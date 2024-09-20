package com.vnpt.authentication.repository;

import com.vnpt.authentication.entity.UserRoleRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRelRepository extends JpaRepository<UserRoleRelEntity, String> {

    List<UserRoleRelEntity> findByRoleId(String roleId);
    List<UserRoleRelEntity> findByUserId(String userId);
}
