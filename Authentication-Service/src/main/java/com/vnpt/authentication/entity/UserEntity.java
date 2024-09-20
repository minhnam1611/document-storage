package com.vnpt.authentication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;


@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "fullname", length = 50)
    private String fullName;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "provider", length = 50)
    private String provider;

    @Column(name = "tenant_id", length = 250)
    private String tenantId;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
