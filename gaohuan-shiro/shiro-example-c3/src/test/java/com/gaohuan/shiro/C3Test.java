package com.gaohuan.shiro;

import com.gaohuan.shiro.entity.User;
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
        userService.changePassword(27l, "nb123");

    }

    @Test
    public void testLogin() {

    }
}
