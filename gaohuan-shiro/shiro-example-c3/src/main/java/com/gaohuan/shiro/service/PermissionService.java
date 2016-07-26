package com.gaohuan.shiro.service;

import com.gaohuan.shiro.entity.Permission;

/**
 * <p>User: acer
 * <p>Date: 2016/7/26
 * <p>Version: 1.0
 */
public interface PermissionService {

    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);
}
