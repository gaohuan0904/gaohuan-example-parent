package com.gaohuan.shiro.dao;

import com.gaohuan.shiro.utils.JdbcTemplateUtils;
import com.gaohuan.shiro.entity.User;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * <p>User: acer
 * <p>Date: 2016/7/26
 * <p>Version: 1.0
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    @Override
    public User createUser(User user) {
        final String sql = "insert into sys_users(username, password, salt, locked) values(?,?,?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                psst.setString(1, user.getUsername());
                psst.setString(2, user.getPassword());
                psst.setString(3, user.getSalt());
                psst.setBoolean(4, user.getLocked());
                return psst;
            }
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public void updateUser(User user) {
        String sql = "update sys_users set username=?,password=? ,salt=?,locked=? where id=?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getLocked(), user.getId());

    }

    @Override
    public void deleteUser(Long userId) {
        String sql = "delete from sys_users where id=? ";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        if (ArrayUtils.isEmpty(roleIds)) {
            return;
        }
        String sql = "insert into sys_users_roles(user_id,role_id)values(?,?)";
        for (Long roleId : roleIds) {
            if (!exist(userId, roleId)) {
                jdbcTemplate.update(sql, userId, roleId);
            }
        }

    }

    @Override
    public void unCorrelationRoles(Long userId, Long... roleIds) {
        if (ArrayUtils.isEmpty(roleIds)) {
            return;
        }
        String sql = "delete from  sys_users_roles where user_id=? and role_id=?";
        for (Long roleId : roleIds) {
            if (exist(userId, roleId)) {
                jdbcTemplate.update(sql, userId, roleId);
            }
        }

    }

    private boolean exist(Long userId, Long roleId) {
        String sql = "select count(1) from sys_users_roles where user_id=? and role_id=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userId, roleId) != 0;
    }

    @Override
    public User findOne(Long userId) {
        String sql = "select id,username,password,salt,locked from sys_users where id=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), userId);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select id,username,password,salt,locked from sys_users where username=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), username);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public Set<String> findRoles(String username) {
        String sql = "select role from sys_users u,sys_roles r ,sys_users_roles ur where u.username=? and u.id=ur.user_id and r.id=ur.role_id";
        return new HashSet<String>(jdbcTemplate.queryForList(sql, String.class, username));
    }

    @Override
    public Set<String> findPermissions(String username) {
        //根据用户名查询role_id
        String sql = "select role_id from sys_users u  ,sys_users_roles ur where u.username=? and u.id=ur.user_id ";
        List<String> roleIdList = jdbcTemplate.queryForList(sql, String.class, username);

        //根据角色id查询权限数据
        sql = "select permission from sys_permissions p ,sys_roles_permissions up where up.role_id in (:roleIds) and p.id=up.permission_id";

        //构造命名查询
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map paramMap = new HashMap();
        paramMap.put("roleIds", roleIdList);

        return new HashSet<String>(namedParameterJdbcTemplate.queryForList(sql, paramMap, String.class));
    }
}
