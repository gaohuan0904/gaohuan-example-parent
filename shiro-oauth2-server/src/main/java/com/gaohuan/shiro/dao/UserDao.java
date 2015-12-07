package com.gaohuan.shiro.dao;

import com.gaohuan.shiro.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {

    public User createUser(User user);

    public User updateUser(User user);

    public void deleteUser(Long userId);

    public void changePassword(Long userId, String newPassword);

    public User findOne(Long userId);

    public List<User> findAll();

    public User findByUsername(String username);


}