package com.gaohuan.shiro.service;

import com.gaohuan.shiro.dao.RoleDao;
import com.gaohuan.shiro.dao.RoleDaoImpl;
import com.gaohuan.shiro.entity.Role;

/**
 * <p>User: acer
 * <p>Date: 2016/7/26
 * <p>Version: 1.0
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId, permissionIds);
    }

    @Override
    public void unCorrelationPermission(Long roleId, Long... permissionIds) {
        roleDao.unCorrelationPermission(roleId, permissionIds);

    }
}
