package com.vnpt.authentication.repository;

import com.vnpt.authentication.entity.TenantEntity;
import com.vnpt.authentication.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepositoty extends JpaRepository<TenantEntity,String> {

    @Query(value = "SELECT t FROM TenantEntity t " +
        "WHERE ( :keySearch is null or t.name like :keySearch ) " +
        "OR ( :keySearch is null or t.topic like :keySearch )")
    Page<TenantEntity> getListTenant(@Param("keySearch") String keySearch,
                                 Pageable pageable);

    TenantEntity findByName(String name);

    TenantEntity findByTopic(String topic);
}
