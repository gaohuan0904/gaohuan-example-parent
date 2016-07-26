package com.gaohuan.shiro.service;

import com.gaohuan.shiro.entity.Role;

/**
 * <p>User: acer
 * <p>Date: 2016/7/26
 * <p>Version: 1.0
 */
public interface RoleService {

    public Role createRole(Role role);

    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);

    public void unCorrelationPermission(Long roleId, Long... permissionIds);
}
