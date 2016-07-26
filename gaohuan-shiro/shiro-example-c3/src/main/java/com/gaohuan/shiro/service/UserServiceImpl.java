package com.gaohuan.shiro.service;

import com.gaohuan.shiro.utils.PasswordHelper;
import com.gaohuan.shiro.dao.UserDao;
import com.gaohuan.shiro.dao.UserDaoImpl;
import com.gaohuan.shiro.entity.User;

import java.util.Set;

/**
 * <p>User: acer
 * <p>Date: 2016/7/26
 * <p>Version: 1.0
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    private PasswordHelper passwordHelper = new PasswordHelper();

    @Override
    public User createUser(User user) {
        passwordHelper.encryptPassword(user);
        return userDao.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userDao.findOne(userId);
        //设置新密码
        user.setPassword(newPassword);
        //加密密码
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);

    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        userDao.correlationRoles(userId, roleIds);
    }

    @Override
    public void unCorrelationRoles(Long userId, Long... roleIds) {
        userDao.unCorrelationRoles(userId, roleIds);
    }

    @Override
    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }
}
