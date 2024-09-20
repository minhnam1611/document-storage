package com.vnpt.authentication.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;


@Entity
@Table(name = "resource")
public class ResourceEntity extends BaseEntity<String> implements Serializable {
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

    @Column(name = "path", length = 20)
    private String path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
