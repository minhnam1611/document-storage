package com.vnpt.authentication.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Entity
@Table(name = "tenant")
public class TenantEntity extends BaseEntity<String> implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;


    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "topic", length = 50)
    private String topic;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
