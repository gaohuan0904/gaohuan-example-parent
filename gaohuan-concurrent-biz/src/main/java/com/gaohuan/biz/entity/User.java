package com.gaohuan.biz.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gh on 2016/3/4 0004.
 */
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = -8636377338869792216L;
    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;

    private String lastname;

    private Integer age;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modify_date")
    private Date modifyDate;

    @Version
    private int version = 0;

    public User() {
    }


    public User(String firstname, Integer age) {
        this.firstname = firstname;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String name) {
        this.firstname = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @PrePersist
    void prePersist() {
        createDate = new Date();
    }

    @PreUpdate
    void preUpdate() {
        modifyDate = new Date();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + firstname + '\'' +
                ", age=" + age +
                '}';
    }
}
