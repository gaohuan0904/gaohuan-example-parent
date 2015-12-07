package com.gaohuan.shiro.service;

import com.gaohuan.shiro.dao.UserDao;
import com.gaohuan.shiro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gh on 2015/12/7.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    public User createUser(User user) {
        return userDao.createUser(user);
    }

    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }

    public void changePassword(Long userId, String newPassword) {
            userDao.changePassword(userId,newPassword);
    }

    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
