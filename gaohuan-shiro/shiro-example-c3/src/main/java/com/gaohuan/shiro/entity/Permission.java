package com.gaohuan.shiro.entity;

import java.io.Serializable;

/**
 * Created by acer on 2016/7/26.
 */
public class Permission implements Serializable {
    private Long id;
    private String permission;
    private String description;
    private Boolean available = Boolean.FALSE;

    public Permission(String permission, String description, Boolean available) {
        this.permission = permission;
        this.description = description;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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

        Permission that = (Permission) o;

        if (!id.equals(that.id)) return false;
        if (!permission.equals(that.permission)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return available != null ? available.equals(that.available) : that.available == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + permission.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}
