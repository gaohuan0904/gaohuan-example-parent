package com.gaohuan.shiro.service;

import com.gaohuan.shiro.entity.User;

import java.util.Set;

/**
 * <p>User: acer
 * <p>Date: 2016/7/26
 * <p>Version: 1.0
 */
public interface UserService {

    public User createUser(User user);

    public void updateUser(User user);

    public void deleteUser(Long userId);

    public void changePassword(Long userId, String newPassword);

    /**
     * 用户关联角色
     *
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(Long userId, Long... roleIds);

    /**
     * 用户取消关联角色
     *
     * @param userId
     * @param roleIds
     */
    public void unCorrelationRoles(Long userId, Long... roleIds);

    public User findOne(Long userId);

    public User findByUsername(String username);

    public Set<String> findRoles(String username);

    public Set<String> findPermissions(String username);
}
