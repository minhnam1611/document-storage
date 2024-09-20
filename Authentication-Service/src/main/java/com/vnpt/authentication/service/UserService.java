package com.vnpt.authentication.service;

import com.vnpt.authentication.entity.RoleEntity;
import com.vnpt.authentication.entity.UserEntity;
import com.vnpt.authentication.entity.UserRoleRelEntity;
import com.vnpt.authentication.repository.RoleRepository;
import com.vnpt.authentication.repository.UserRepository;
import com.vnpt.authentication.repository.UserRoleRelRepository;
import com.vnpt.authentication.security.oauth2.OAuth2AuthenticationProcessingException;
import com.vnpt.authentication.security.oauth2.user.OAuth2UserInfo;
import com.vnpt.authentication.security.oauth2.user.OAuth2UserInfoFactory;
import com.vnpt.authentication.service.dto.LocalUser;
import com.vnpt.authentication.web.rest.dto.SocialProvider;
import com.vnpt.authentication.web.rest.request.manage_user.CreateUserRequest;
import com.vnpt.authentication.web.rest.request.manage_user.UpdateUserRequest;
import com.vnpt.authentication.web.rest.response.manage_user.GetDetailUserResponse;
import com.vnpt.authentication.web.rest.response.manage_user.GetListUserResponse;
import com.vnpt.common.base.BaseResponse;
import com.vnpt.common.base.PagingRequest;
import com.vnpt.common.constants.Constant;
import com.vnpt.common.constants.ErrorCodes;
import com.vnpt.common.constants.UserConstant;
import com.vnpt.interceptor.dto.AccountDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRelRepository userRoleRelRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(
        UserRepository userRepository,
        RoleRepository roleRepository,
        UserRoleRelRepository userRoleRelRepository,
        BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRelRepository = userRoleRelRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<BaseResponse<GetListUserResponse>> getListUser(PagingRequest request, Pageable pageable) {
        GetListUserResponse data = new GetListUserResponse();
        String keySearch = "";
        if (request.getFilter() == null || request.getFilter().isEmpty()) {
            keySearch = null;
        } else {
            keySearch = "%" + request.getFilter() + "%";
        }

        String username = ((AccountDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        List<String> roleList = ((AccountDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserInfo().getRoleInfo();
        Page<UserEntity> listUser = null;
        if (roleList.contains(Constant.ROLE_ADMIN_SUPREME)) {
            listUser = userRepository.getListUserSupperAdmin(keySearch, pageable);
        } else {
            listUser = userRepository.getListUser(keySearch, username, pageable);
        }

        data.setData(listUser.getContent());
        data.setSizeOfPage(listUser.getSize());
        data.setRecordsTotal(listUser.getTotalElements());
        data.setRecordsFiltered((int) listUser.getTotalElements());
        data.setTotalPages(listUser.getTotalPages());

        BaseResponse<GetListUserResponse> response = new BaseResponse<>();
        response.success(data);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseResponse<GetDetailUserResponse>> getUserDetail(String id) {
        GetDetailUserResponse data = new GetDetailUserResponse();
        Optional<UserEntity> userInfo = userRepository.findById(id);
        if (userInfo.isPresent()) {
            data.setUserInfo(userInfo.get());
            List<String> listRole = userRepository.getRolesOfUser(userInfo.get().getId());
            data.setRoleInfo(listRole);
        }
        BaseResponse<GetDetailUserResponse> response = new BaseResponse<>();
        response.success(data);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseResponse<String>> deleteUser(String id) {
        BaseResponse<String> response = new BaseResponse<>();
        Optional<UserEntity> userInfo = userRepository.findById(id);
        if (userInfo.isPresent()) {
            UserEntity userDelete = userInfo.get();
            String currentUsername = ((AccountDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            if (currentUsername.equals(userDelete.getUsername())) {
                response.error(ErrorCodes.DELETE_USER_FAIL);
                return ResponseEntity.ok(response);
            }
            userRepository.delete(userInfo.get());
            response.success("Deleted UserID: " + id);
        } else {
            response.setCode(ErrorCodes.NOT_FOUND_USER.code);
            response.setDesc(ErrorCodes.NOT_FOUND_USER.message);
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseResponse<GetDetailUserResponse>> createUser(CreateUserRequest request) {
        //Check username
        UserEntity user = userRepository.findByUsername(request.getUsername());
        GetDetailUserResponse data = new GetDetailUserResponse();
        BaseResponse<GetDetailUserResponse> response = new BaseResponse<>();
        if (user == null) {
            UserEntity newUser = new UserEntity();
            newUser.setFullName(request.getFullname());
            newUser.setUsername(request.getUsername());
            newUser.setPhone(request.getPhone());
            newUser.setEmail(request.getEmail());
            newUser.setStatus(UserConstant.USER_ACTIVE);

            String randomPass = this.genPassword();
            System.out.println("Pass: " + randomPass);
            String encodePass = bCryptPasswordEncoder.encode(randomPass);

            //Do send email Password
            //TODO

            newUser.setPassword(encodePass);

            //Do set Create By
            newUser.setCreatedBy(UserConstant.SYSTEM); // TODO

            UserEntity userCreated = userRepository.save(newUser);

            List<String> roles = request.getRoles();

            roles.forEach(role -> {
                UserRoleRelEntity ur = new UserRoleRelEntity();
                ur.setUserId(userCreated.getId());
                ur.setRoleId(role);
                ur.setCreatedBy(UserConstant.SYSTEM); // TODO
                userRoleRelRepository.save(ur);
            });
            data.setUserInfo(userCreated);
            data.setRoleInfo(roles);

            response.success(data);
        } else {
            response.setCode(ErrorCodes.NOT_FOUND_USER.code);
            response.setCode(ErrorCodes.NOT_FOUND_USER.message);
        }

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseResponse<GetDetailUserResponse>> updateUser(UpdateUserRequest request) {
        GetDetailUserResponse data = new GetDetailUserResponse();
        BaseResponse<GetDetailUserResponse> response = new BaseResponse<>();

        //Check ID user
        Optional<UserEntity> otpOldUser = userRepository.findById(request.getUserId());
        if (otpOldUser.isEmpty()) {
            response.setCode(ErrorCodes.NOT_FOUND_USER.code);
            response.setCode(ErrorCodes.NOT_FOUND_USER.message);
        } else {
            UserEntity oldUser = otpOldUser.get();
            oldUser.setEmail(request.getEmail());
//            oldUser.setUsername();
            oldUser.setFullName(request.getFullname());
            oldUser.setStatus(request.getStatus());
            oldUser.setPhone(request.getPhone());
            oldUser.setLastModifiedBy(UserConstant.SYSTEM); //TODO

            userRepository.save(oldUser);

            data.setUserInfo(oldUser);
            //Update Role

            List<UserRoleRelEntity> oldRoles = userRoleRelRepository.findByUserId(oldUser.getId());

            if (!request.getRoles().isEmpty()) {
                userRoleRelRepository.deleteAll(oldRoles);

                request
                    .getRoles()
                    .forEach(role -> {
                        UserRoleRelEntity ur = new UserRoleRelEntity();
                        ur.setUserId(oldUser.getId());
                        ur.setRoleId(role);
                        ur.setLastModifiedBy(UserConstant.SYSTEM); // TODO
                        userRoleRelRepository.save(ur);
                    });
                data.setRoleInfo(request.getRoles());
            }
            response.success(data);
        }
        return ResponseEntity.ok(response);
    }


    private String genPassword() {
        String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
        String DIGITS = "0123456789";
        String SPECIAL_CHARS = "!@#$%^&*()";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        password.append(UPPER_CASE.charAt(random.nextInt(UPPER_CASE.length())));
        for (int i = 0; i < 4; i++) {
            password.append(LOWER_CASE.charAt(random.nextInt(LOWER_CASE.length())));
        }
        for (int i = 0; i < 2; i++) {
            password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        return password.toString();
    }


    @Transactional
    public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
            throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
        } else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

//        SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);

        UserEntity user = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        if (user != null) {
            if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
                throw new OAuth2AuthenticationProcessingException(
                    "Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
            }
//            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(registrationId, oAuth2UserInfo);
        }

        return LocalUser.create(user, attributes, idToken, userInfo);
    }

    @Transactional(value = "transactionManager")
    public UserEntity registerNewUser(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        UserEntity user = UserEntity.builder()
            .username(oAuth2UserInfo.getEmail())
            .fullName(oAuth2UserInfo.getName())
            .email(oAuth2UserInfo.getEmail())
            .status("ACTIVE")
            .provider(registrationId)
            .password("")
            .build();
        UserEntity newUser = userRepository.save(user);
        userRepository.flush();

        RoleEntity role = roleRepository.findByCode(Constant.ROLE_DEFAULT);
        UserRoleRelEntity userRoleRel = new UserRoleRelEntity();
        userRoleRel.setUserId(newUser.getId());
        userRoleRel.setRoleId(role.getId());
        userRoleRelRepository.save(userRoleRel);

        return newUser;
    }
}
