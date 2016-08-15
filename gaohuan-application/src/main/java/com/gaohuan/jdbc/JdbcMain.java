package com.gaohuan.jdbc;

import com.gaohuan.userdata.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/10
 */
public class JdbcMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:user-data-generator.xml");
        NamedParameterJdbcTemplate jdbcTemplate = context.getBean(NamedParameterJdbcTemplate.class);

        String sql = "select * from zk.t_user where uid=:uid";

        Map paramMap = new HashMap();
        paramMap.put("uid", "0004701a90e64d5daf27da1e022b87bf");

        //行计数回调处理对象
        RowCountCallbackHandler rowCount = new RowCountCallbackHandler();
        jdbcTemplate.query(sql, paramMap, rowCount);
        rowCount.getRowCount();

        //对map的简单封装
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        jdbcTemplate.query(sql, parameterSource, rowCount);
        rowCount.getRowCount();

        //使用时命名参数必须和JavaBean属性名称相对
        sql = "select * from zk.t_user where user_code=:usercode";

        User user = new User();
        user.setUsercode("UC201608099252509");

        BeanPropertySqlParameterSource beanParameterSource = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.query(sql, beanParameterSource, rowCount);
        rowCount.getRowCount();

    }
}
