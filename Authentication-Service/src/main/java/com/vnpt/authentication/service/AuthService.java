package com.vnpt.authentication.service;


import com.vnpt.authentication.config.jwt.TokenProviderJWT;
import com.vnpt.authentication.entity.RoleEntity;
import com.vnpt.authentication.entity.TenantEntity;
import com.vnpt.authentication.entity.UserEntity;
import com.vnpt.authentication.entity.UserRoleRelEntity;
import com.vnpt.authentication.repository.RoleRepository;
import com.vnpt.authentication.repository.TenantRepositoty;
import com.vnpt.authentication.repository.UserRepository;
import com.vnpt.authentication.repository.UserRoleRelRepository;
import com.vnpt.authentication.service.dto.PermissionInfo;
import com.vnpt.authentication.service.dto.PrincipalInfo;
import com.vnpt.authentication.service.dto.TenantInfo;
import com.vnpt.authentication.service.dto.UserPrincipal;
import com.vnpt.authentication.utilities.Utils;
import com.vnpt.authentication.web.rest.request.auth.LoginRequest;
import com.vnpt.authentication.web.rest.request.auth.RegisterRequest;
import com.vnpt.authentication.web.rest.response.auth.LoginResponse;
import com.vnpt.authentication.web.rest.response.auth.RegisterResponse;
import com.vnpt.authentication.web.rest.response.manage_user.GetDetailUserResponse;
import com.vnpt.common.base.BaseResponse;
import com.vnpt.common.constants.Constant;
import com.vnpt.common.constants.ErrorCodes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRoleRelRepository userRoleRelRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenProviderJWT tokenProviderJWT;

    @Autowired
    private TenantRepositoty tenantRepositoty;



//    @Override
//    public AccountDetail loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userRepository.findByUsername(username);
//        if (user == null) {
//            return null;
//        }
//        Set<String> lstPers = getListPermissionByUser(user);
//        return new AccountDetail(user, lstPers);
//    }

    public Set<String> getListPermissionByUser(UserEntity user) {
        List<PermissionInfo> rs = userRepository.getPermissionOfUser(user.getId());
        Set<String> lstPermission = new HashSet<>();
        rs.forEach(info -> {
            String srcCode = info.getSrcCode();
            String actions = info.getActions();
            List<String> lstAction = List.of(actions.split(","));
            lstAction.forEach(ac -> {
                lstPermission.add(srcCode + "." + Constant.mapAction.get(ac));
            });
        });

        return lstPermission;
    }

    public ResponseEntity<BaseResponse<LoginResponse>> login(LoginRequest request) {
        LoginResponse data = new LoginResponse();
        BaseResponse<LoginResponse> response = new BaseResponse<>();
        UserEntity checkUser = userRepository.findByUsername(request.getUsername());
        if (checkUser == null) {
            response.setCode(ErrorCodes.USERNAME_NOT_EXIST.code);
            response.setDesc(ErrorCodes.USERNAME_NOT_EXIST.message);
            response.setData(null);
        } else {
            boolean checkPass = bCryptPasswordEncoder.matches(request.getPassword(), checkUser.getPassword());
            if (checkPass) {
                data.setToken(tokenProviderJWT.generateToken(checkUser.getUsername()));
                data.setRefreshToken(tokenProviderJWT.generateRefreshToken(checkUser.getUsername()));
                response.success(data);
            } else {
                response.setCode(ErrorCodes.IVALID_LOGIN.code);
                response.setDesc(ErrorCodes.IVALID_LOGIN.message);
                response.setData(null);
            }
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseResponse<GetDetailUserResponse>> getUserByToken(HttpServletRequest request){
        GetDetailUserResponse data = new GetDetailUserResponse();
        BaseResponse<GetDetailUserResponse> response = new BaseResponse<>();
        String token = Utils.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && tokenProviderJWT.validateToken(token)) {
            String username = tokenProviderJWT.getUsernameFromJWT(token);
            UserEntity userInfo = userRepository.findByUsername(username);
            if (userInfo != null) {
                data.setUserInfo(userInfo);
                List<String> listRole = userRepository.getRolesOfUser(userInfo.getId());
                data.setRoleInfo(listRole);
            }

            response.success(data);
        }
        return ResponseEntity.ok(response);
    }
    @Transactional
    public ResponseEntity<BaseResponse<RegisterResponse>> register(RegisterRequest request){
        RegisterResponse data = new RegisterResponse();
        BaseResponse<RegisterResponse> response = new BaseResponse<>();
        //Check
        if(checkExistEmail(request.getEmail())){
            response.setCode(ErrorCodes.SERVER_ERROR.code);
            response.setDesc(ErrorCodes.SERVER_ERROR.message);
            return ResponseEntity.ok(response);
        }
        if(checkExistUsername(request.getUsername())){
            response.setCode(ErrorCodes.USERNAME_EXISTED.code);
            response.setDesc( ErrorCodes.USERNAME_EXISTED.message);
            return ResponseEntity.ok(response);
        }


        UserEntity newUser = new UserEntity();
        newUser.setUsername(request.getUsername());
        newUser.setStatus(Constant.STATUS_ACTIVE);
        newUser.setPhone(request.getPhone());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        newUser.setFullName(request.getFullName());
        newUser.setCreatedBy(Constant.USER);

        RoleEntity role = roleRepository.findByCode(Constant.ROLE_DEFAULT);
        if(role == null || !role.getStatus().equals(Constant.STATUS_ACTIVE)){
            response.setCode(ErrorCodes.SERVER_ERROR.code);
            response.setDesc(ErrorCodes.SERVER_ERROR.message);
            return ResponseEntity.ok(response);
        }
        UserEntity userCreated = userRepository.save(newUser);
        UserRoleRelEntity userRoleRelEntity = new UserRoleRelEntity();
        userRoleRelEntity.setRoleId(role.getId());
        userRoleRelEntity.setUserId(userCreated.getId());
        userRoleRelEntity.setCreatedBy(Constant.USER);
        userRoleRelRepository.save(userRoleRelEntity);
        data.setUserInfo(userCreated);
        response.success(data);


        return ResponseEntity.ok(response);
    }


    private boolean checkExistEmail(String email){

        UserEntity user = userRepository.findByEmail(email);
        return user != null;
    }
    private boolean checkExistUsername(String username){
        UserEntity user = userRepository.findByUsername(username);
        return user != null;
    }


    public ResponseEntity<UserPrincipal> getUserPrincipal(HttpServletRequest request){
        UserPrincipal data = new UserPrincipal();
        String token = Utils.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && tokenProviderJWT.validateToken(token)) {
            String username = tokenProviderJWT.getUsernameFromJWT(token);
            UserEntity userInfo = userRepository.findByUsername(username);
            if (userInfo != null) {
                data.setUsername(userInfo.getUsername());
                data.setEmail(userInfo.getEmail());
                data.setPhone(userInfo.getPhone());

                List<String> listRole = userRepository.getRolesOfUser(userInfo.getId());
                data.setRoleInfo(listRole);

                List<PrincipalInfo> principalInfos = userRepository.getPrincipal(userInfo.getId());
                data.setPrincipalInfo(principalInfos);

                if(userInfo.getTenantId() != null ){
                    Optional<TenantEntity> tenantOpt = tenantRepositoty.findById(userInfo.getTenantId());
                    if(tenantOpt.isPresent()){
                        TenantEntity tenant = tenantOpt.get();
                        TenantInfo tenantInfo = new TenantInfo();
                        tenantInfo.setId(tenant.getId());
                        tenantInfo.setName(tenant.getName());
                        tenantInfo.setTopic(tenant.getTopic());
                        data.setTenantInfo(tenantInfo);
                    }
                }

            }

        }else {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(data);
    }

    public ResponseEntity<BaseResponse<LoginResponse>> refreshToken(HttpServletRequest request) {
        LoginResponse data = new LoginResponse();
        BaseResponse<LoginResponse> response = new BaseResponse<>();
        String token = Utils.getJwtFromRequest(request);
        if (StringUtils.hasText(token) && tokenProviderJWT.validateToken(token)) {
            String username = tokenProviderJWT.getUsernameFromJWT(token);
            data.setToken(tokenProviderJWT.generateToken(username));
            data.setRefreshToken(tokenProviderJWT.generateRefreshToken(username));
        }
        response.success(data);
        return  ResponseEntity.ok(response);
    }
}
