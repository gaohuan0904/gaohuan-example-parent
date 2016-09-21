package com.gaohuan;

import com.gaohuan.userdata.User;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import java.time.LocalDate;

/**
 * Created by gh on 2016/5/24 0024.
 */
public class Main {
    public static void main(String[] args) {
        /*
        try {
            Class<HelloWorld> t = (Class<HelloWorld>) Class.forName("com.gaohuan.HelloWorld");
            HelloWorld h = t.newInstance();
            h.hello();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());
        User user = new User();
        user.setUid("aaaa");
        user.setName("bbbb");
        user.setSex("1");
        BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(user);
        source.getReadablePropertyNames();


    }

}
