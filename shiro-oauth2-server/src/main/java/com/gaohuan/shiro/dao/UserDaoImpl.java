package com.gaohuan.shiro.dao;

import com.gaohuan.shiro.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by gh on 2015/12/7.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User createUser(final User user) {
        final String sql = "INSERT  INTO  oauth2_user(username,password,salt) VALUES (?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement psmt = con.prepareStatement(sql, new String[]{"id"});
                psmt.setString(1, user.getUsername());
                psmt.setString(2, user.getPassword());
                psmt.setString(3, user.getSalt());
                return psmt;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    public User updateUser(User user) {
        if (user == null) {
            return null;
        }
        String sql = "update oauth2_user set username=?,password=?,salt=? where id=?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getSalt());

        return user;
    }

    public void deleteUser(Long userId) {
        if (userId == null) return;
        jdbcTemplate.update("delete FROM oauth2_user WHERE  id=?", userId);

    }

    public void changePassword(Long userId, String newPassword) {
        final String sql = "update oauth2_user set password=? where id=? ";
        jdbcTemplate.update(sql, newPassword, userId);

    }

    public User findOne(Long userId) {
        String sql = "SELECT  * FROM oauth2_user where id=?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), userId);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    public List<User> findAll() {
        String sql = "SELECT  * FROM oauth2_user ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
    }

    public User findByUsername(String username) {
        String sql = "SELECT  * FROM oauth2_user where username=?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), username);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }
}
