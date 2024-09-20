package com.vnpt.authentication.repository;

import com.vnpt.authentication.entity.UserEntity;
import com.vnpt.authentication.service.dto.PermissionInfo;
import com.vnpt.authentication.service.dto.PrincipalInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    @Query(value = "SELECT u FROM UserEntity u " +
        "WHERE (( :keySearch is null or u.username like :keySearch ) " +
        "OR ( :keySearch is null or u.fullName like :keySearch )) " +
        "AND u.createdBy = :username ")
    Page<UserEntity> getListUser(@Param("keySearch") String keySearch,
                                 @Param("username") String username,
                                    Pageable pageable);

    @Query(value = "SELECT u FROM UserEntity u " +
        "WHERE ( :keySearch is null or u.username like :keySearch ) " +
        "OR ( :keySearch is null or u.fullName like :keySearch )")
    Page<UserEntity> getListUserSupperAdmin(@Param("keySearch") String keySearch,
                                 Pageable pageable);

    @Query(value = "select r.name from users u " +
        "inner join user_role_rel ur on u.id = ur.user_id " +
        "inner join role r on ur.role_id = r.id " +
        "where u.id = :id ", nativeQuery = true)
    List<String> getRolesOfUser(@Param("id") String userId);


    @Query(value = "select per.code as src_code, rr.action as actions   from role r " +
        "    inner join user_role_rel ur on r.id = ur.role_id " +
        "    inner join role_resource_rel rr on r.id = rr.role_id " +
        "    inner join resource per on rr.resource_id = per.id " +
        "where ur.user_id = :id",nativeQuery = true)
    List<PermissionInfo> getPermissionOfUser(@Param("id") String idUser);

    @Query(value = "select  src.path , array_to_string(array_agg(rr.action), ',') as action " +
        "from users u inner join user_role_rel ur on u.id = ur.user_id " +
        "inner join role r on r.id = ur.role_id " +
        "inner join role_resource_rel rr on r.id = rr.role_id " +
        "inner join resource src on rr.resource_id = src.id " +
        "where u.id = :id " +
        "group by src.path",nativeQuery = true)
    List<PrincipalInfo> getPrincipal(@Param("id") String idUser);

}
