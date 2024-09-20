package com.vnpt.authentication.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;


@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity<String> implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 250)
    private String desc;

    @Column(name = "status", length = 250)
    private String status;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
