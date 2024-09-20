package com.vnpt.authentication.repository;

import com.vnpt.authentication.entity.ResourceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, String> {
    @Query(value = "SELECT r FROM ResourceEntity r " +
        "WHERE ( :keySearch is null or r.name like :keySearch ) " +
        "OR ( :keySearch is null or r.code like :keySearch )")
    Page<ResourceEntity> getListSrc(@Param("keySearch") String keySearch,
                                 Pageable pageable);
}
