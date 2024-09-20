package com.vnpt.authentication.repository;

import com.vnpt.authentication.entity.RoleResourceRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleResourceRepository extends JpaRepository<RoleResourceRelEntity, String> {
    List<RoleResourceRelEntity> findByRoleId(String roleId);

    List<RoleResourceRelEntity> findByResourceId( String srcId);
}
