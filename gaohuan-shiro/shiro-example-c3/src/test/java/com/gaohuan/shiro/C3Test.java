package com.gaohuan.shiro;

import com.gaohuan.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>User: acer
 * <p>Date: 2016/7/26
 * <p>Version: 1.0
 */
public class C3Test extends BaseTest {

    @Test
    public void testService() {
        User user = userService.findByUsername("zhang");
        userService.findRoles("zhang");
        userService.findPermissions("zhang");
        userService.changePassword(27l, "123");

    }

    @Test
    public void testLogin() {
        login("classpath:shiro.ini", "zhang", "123");
        Assert.assertTrue(SecurityUtils.getSubject().isAuthenticated());
    }

    @Test(expected = UnknownAccountException.class)
    public void testLoginFailWithUnknownUsername() {
        login("classpath:shiro.ini", "zhang11", "123");
    }

    @Test(expected = IncorrectCredentialsException.class)
    public void testLoginFailWithErrorPassword() {
        login("classpath:shiro.ini", "zhang", "123112");
    }

    @Test(expected = LockedAccountException.class)
    public void testLoginFailWithLocked() {
        login("classpath:shiro.ini", "li", "123");
    }

    @Test(expected = ExcessiveAttemptsException.class)
    public void testLoginFailWithRetryCount() {
        for (int i = 0; i < 5; i++) {
            try {
                login("classpath:shiro.ini", "zhang", "1231");
            } catch (Exception e) {
               /*ignore*/
            }
        }
        login("classpath:shiro.ini", "zhang", "1231");
    }

    @Test
    public void testHasRole() {
        login("classpath:shiro.ini", "gaohaun", "123");
        Assert.assertTrue(subject().hasRole("admin"));

    }


}
