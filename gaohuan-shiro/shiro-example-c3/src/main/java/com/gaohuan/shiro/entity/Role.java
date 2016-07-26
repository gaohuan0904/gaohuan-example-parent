package com.gaohuan.shiro.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by acer on 2016/7/26.
 */
public class Role implements Serializable {
    private Long id;
    private String role;//角色标识
    private String description;//角色描述
    private Boolean available = Boolean.FALSE;

    public Role() {

    }

    public Role(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(id, role1.id) &&
                Objects.equals(role, role1.role) &&
                Objects.equals(description, role1.description) &&
                Objects.equals(available, role1.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, description, available);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}
