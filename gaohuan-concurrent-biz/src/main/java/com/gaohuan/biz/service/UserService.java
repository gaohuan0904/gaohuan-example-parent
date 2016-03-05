package com.gaohuan.biz.service;

import com.gaohuan.biz.entity.User;

/**
 * 用户基础服务类
 */
public interface UserService {

    public User save(User user);

    /**
     * 根据姓名查询用户信息
     *
     * @param username
     * @return
     */
    public User findByUsername(String username);
}
