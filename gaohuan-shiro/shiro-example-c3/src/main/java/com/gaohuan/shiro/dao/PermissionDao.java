package com.gaohuan.shiro.dao;

import com.gaohuan.shiro.entity.Permission;

/**
 * <p>User: acer
 * <p>Date: 2016/7/26
 * <p>Version: 1.0
 */
public interface PermissionDao {

    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);
}
