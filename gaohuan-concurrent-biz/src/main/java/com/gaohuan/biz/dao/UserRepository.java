package com.gaohuan.biz.dao;

import com.gaohuan.biz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gh on 2016/3/4 0004.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
