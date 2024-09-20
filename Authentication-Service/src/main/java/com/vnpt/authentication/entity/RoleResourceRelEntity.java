package com.vnpt.authentication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_resource_rel")
public class RoleResourceRelEntity extends BaseEntity<String> implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(name = "role_id", length = 50)
    private String roleId;

    @Column(name = "resource_id", length = 50)
    private String resourceId;

    @Column(name = "action")
    private String action;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
