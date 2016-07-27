package com.gaohuan.shiro;

import com.gaohuan.shiro.utils.JdbcTemplateUtils;
import com.gaohuan.shiro.entity.Permission;
import com.gaohuan.shiro.entity.Role;
import com.gaohuan.shiro.entity.User;
import com.gaohuan.shiro.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;


/**
 * <p>User: acer
 * <p>Date: 2016/7/26
 * <p>Version: 1.0
 */
public abstract class BaseTest {

    protected PermissionService permissionService = new PermissionServiceImpl();
    protected UserService userService = new UserServiceImpl();
    protected RoleService roleService = new RoleServiceImpl();


    public void setUp() {
        JdbcTemplateUtils.jdbcTemplate().update("delete from shiro.sys_users");
        JdbcTemplateUtils.jdbcTemplate().update("delete from shiro.sys_roles");
        JdbcTemplateUtils.jdbcTemplate().update("delete from shiro.sys_permissions");
        JdbcTemplateUtils.jdbcTemplate().update("delete from shiro.sys_users_roles");
        JdbcTemplateUtils.jdbcTemplate().update("delete from shiro.sys_roles_permissions");

        Permission p1 = new Permission("user:create", "用户模块新增", Boolean.TRUE);
        Permission p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
        Permission p3 = new Permission("menu:create", "菜单模块新增", Boolean.TRUE);
        permissionService.createPermission(p1);
        permissionService.createPermission(p2);
        permissionService.createPermission(p3);

        Role r1 = new Role("admin", "管理员", Boolean.TRUE);
        Role r2 = new Role("user", "用户管理员", Boolean.TRUE);
        roleService.createRole(r1);
        roleService.createRole(r2);

        //关联角色-权限
        roleService.correlationPermissions(r1.getId(), p1.getId());
        roleService.correlationPermissions(r1.getId(), p2.getId());
        roleService.correlationPermissions(r1.getId(), p3.getId());

        roleService.correlationPermissions(r2.getId(), p1.getId());
        roleService.correlationPermissions(r2.getId(), p2.getId());

        //新增用户
        String password = "123";
        User u1 = new User("gaohaun", password);
        User u2 = new User("wang", password);
        User u3 = new User("zhang", password);
        User u4 = new User("li", password);
        u4.setLocked(Boolean.TRUE);
        userService.createUser(u1);
        userService.createUser(u2);
        userService.createUser(u3);
        userService.createUser(u4);

        //关联用户-角色
        userService.correlationRoles(u1.getId(), r1.getId());
        userService.correlationRoles(u2.getId(), r1.getId());
        userService.correlationRoles(u3.getId(), r2.getId());
        userService.correlationRoles(u4.getId(), r2.getId());


    }

    @After
    public void tearDown() {
        ThreadContext.unbindSubject();

    }

    public void login(String configFile, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        subject.login(new UsernamePasswordToken(username, password));


    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }

}
