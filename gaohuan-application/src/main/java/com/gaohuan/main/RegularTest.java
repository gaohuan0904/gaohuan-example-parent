package com.gaohuan.main;

import java.util.regex.Pattern;

/**
 * Created by gh on 2016/1/12.
 */
public class RegularTest {


    public static void main(String[] args) {
        /*
        String password="1234561";
        String pattern="^(?=.*\\d.*)(?=.*[a-zA-Z].*).{6,18}$";
            Pattern mobilePattern = Pattern.compile(pattern);
            Matcher m = mobilePattern.matcher(password);
        if(m.matches()){
            System.out.println(password+"="+"成功" );
        }else {
            System.out.println(password+"="+"失败" );

        }

        */
        String test = "a23456";
        Pattern pattern = Pattern.compile("^[0-9]{6}$");
        System.out.println(pattern.matcher(test).matches());


    }
}

