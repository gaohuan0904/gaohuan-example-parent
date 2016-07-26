package com.gaohuan.shiro.service;

import com.gaohuan.shiro.dao.PermissionDao;
import com.gaohuan.shiro.dao.PermissionDaoImpl;
import com.gaohuan.shiro.entity.Permission;

/**
 * <p>User: acer
 * <p>Date: 2016/7/26
 * <p>Version: 1.0
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    @Override
    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
