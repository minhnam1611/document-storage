package com.vnpt.authentication.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.Instant;


@Entity
@Table(name = "user_role_rel")
public class UserRoleRelEntity extends BaseEntity<String> implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(name = "user_id", length = 50)
    private String userId;

    @Column(name = "role_id", length = 50)
    private String roleId;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public UserRoleRelEntity(String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate, String id, String userId, String roleId) {
        super(createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRoleRelEntity() {
    }
}
