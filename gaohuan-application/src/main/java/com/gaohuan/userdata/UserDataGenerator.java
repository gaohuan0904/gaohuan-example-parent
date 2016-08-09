package com.gaohuan.userdata;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * 批量插入用户表数据
 * <p>User: GaoHuan
 * <p>Date: 2016/8/9
 */
public class UserDataGenerator {

    public static List<String> phoneList = new ArrayList<String>();
    public static List<String> nickNameList = new ArrayList<String>();
    public static int index = 1;

    //读取文件
    static {
        try {
            String phonePath = UserDataGenerator.class.getResource("/userdata/mobile.txt").getPath();
            String nickNamePath = UserDataGenerator.class.getResource("/userdata/nickName.txt").getPath();
            phoneList = FileUtils.readLines(new File(phonePath));
            nickNameList = FileUtils.readLines(new File(nickNamePath));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:user-data-generator.xml");
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        String sql = "INSERT INTO zk.t_user " +
                "(uid, user_code, phone, user_name, sex, create_date,channel,delete_flag,password)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        List<User> userList = generateUsers();

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User user = userList.get(i);
                ps.setString(1, user.getUid());
                ps.setString(2, user.getUsercode());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getName());
                ps.setString(5, user.getSex());
                ps.setDate(6, user.getCreateDate());
                ps.setString(7, user.getChannel());
                ps.setString(8, user.getDeleteFlag());
                ps.setString(9, user.getPassword());
            }

            @Override
            public int getBatchSize() {
                return userList.size();
            }
        });


    }

    public static List<User> generateUsers() {
        List<User> userList = new ArrayList<User>();
        Calendar calendar = Calendar.getInstance();
        Date nowDate = new Date(calendar.getTimeInMillis());
        String strDate = DateFormatUtils.format(calendar, "yyyyMMdd");

        for (int i = 0; i < 5000; i++) {
            User user = new User();
            user.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
            user.setUsercode("UC" + strDate + RandomStringUtils.randomNumeric(6));
            user.setPhone(getPhone());
            user.setName(getNickName());
            user.setSex(getSex());
            user.setCreateDate(nowDate);
            user.setPassword("e10adc3949ba59abbe56e057f20f883e");
            user.setChannel("3");
            user.setDeleteFlag("0");
            user.setStatus("449700090001");
            userList.add(user);
            index++;
        }

        return userList;
    }

    public static String getSex() {
        return (index % 2 == 0) ? "449700160001" : "449700160002";
    }

    public static String getPhone() {
        String phone = null;
        if (index < phoneList.size()) {
            phone = phoneList.get(index);
        }
        if (StringUtils.isBlank(phone)) {
            phone = RandomStringUtils.randomNumeric(11);
        }
        return phone;
    }

    public static String getNickName() {
        String nickName = null;
        if (index < nickNameList.size()) {
            nickName = nickNameList.get(index);
        }
        if (StringUtils.isBlank(nickName)) {
            nickName = RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(5, 20));
        }
        return nickName;
    }


}
