package com.gaohuan.biz.dao;

import com.gaohuan.biz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by gh on 2016/3/4 0004.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 根据姓名查询用户信息
     *
     * @param username
     * @return
     */
    @Query("select u from User  u where  u.name=?1")
    public User findByUsername(String username);
}
