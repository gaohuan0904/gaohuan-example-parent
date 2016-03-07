package com.gaohuan.biz.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gh on 2016/3/4 0004.
 */
@Entity

public class User {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer age;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "modify_date", nullable = false)
    private Date modifyDate;

    @Version
    private int version = 0;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @PrePersist
    void prePersist() {
        createDate = new Date();
    }

    @PreUpdate
    void preUpdate() {
        modifyDate = new Date();
    }
}
