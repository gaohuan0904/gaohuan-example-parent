package com.gaohuan.regex;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 预定义字符类
 * . 任何字符
 * \d 数字字符[0-9]
 * \D 非数字字符[^0-9]
 * \s 空白字符[\t\n\x0B\f\r]
 * \S 非空白字符 [^\s]
 * \w 单词字符 [a-zA-Z_0-9]
 * \W 非单词字符
 * <p>
 * <p>User: acer
 * <p>Date: 2016/7/29
 * <p>Version: 1.0
 */
public class RegexUtils {
    public static void main(String[] args) {
        /*
        Pattern pattern = Pattern.compile("[2-8&&[4-6]]+");//包含
        Pattern pattern = Pattern.compile("[2-8&&[^4-6]]+");//排除
        Pattern pattern = Pattern.compile("\\D+");//非数字字符
        Pattern pattern = Pattern.compile("\\s+");
        Pattern pattern = Pattern.compile("\\w+");
        */
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();


    }

    public static void printMatchers(String regex, String s) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    public static void print(String regex, String s) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        matcher.find();
        System.out.println(matcher.group());
    }

    public static void matches(String regex, String s) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        System.out.println(matcher.matches());
    }

    //反向引用
    public static void test1() {
        String s = "3344";
        Pattern pattern = Pattern.compile("(\\d)\\1");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        System.out.println("-------------------");
    }

    //反向引用
    public static void test2() {
        String s = "mms/ssa/bbb";
        Pattern pattern = Pattern.compile("/");
        String[] result = pattern.split(s);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    //边界匹配
    public static void test3() {
        String s = "hello world regex ";

        String regex = "\\bhello";//单词边界
        print(regex, s);
        regex = "wor\\B";//
        printMatchers(regex, s);


    }

    /**
     * 非捕获组：以(?)开头的组是非捕获组，它不捕获文本，也不参与计数。
     * (?=X) 表达式X在位置的右侧匹配到时才能继续匹配
     * (?!=X) 表达式X不在此位置的右侧匹配到时才继续匹配。
     * (?<=X) 表达式X在此位置的左侧匹配到时才继续匹配
     * (?!=X) 表达式X不在此位置的左侧匹配到时才继续匹配。
     */
    public static void test4() {
        //京东一道测试题，当时不会..原来是用非捕获组，这个应该是记住了
        String s = "Q_12345_2";//以尽可能高性能的方式一次获取中间的数字。
        String regex = "(?<=\\w+_)(\\d+)(?=_\\d)";
        print(regex, s);
    }

    /**
     * 非捕获组，二选一时使用
     */
    public static void test5() {
        String s = "industry_industries";//以尽可能高性能的方式一次获取中间的数字。
        String regex = "industr(?:y|ies)";
        printMatchers(regex, s);
    }

    /**
     * 常用的正则表达式写法
     */
    public static void test6() {
        String s = "你好sasfa123";
        String regex = "^[\u4e00-u9fa5]{0,}$";//汉字
        regex = "^[a-zA-Z0-9]+$";//英文和数字
        regex = "^.{3,20}$";//长度为3-20的所有字符
        regex = "^\\w{6,20}";//长度为6-20的数字、字母、下划线组成的字符
        regex = "[\u4e00-\u9fa5\\w]+$";//中文、英文、数字、下划线
        regex = "^%&',;=?$\0x22";//中文、英文、数字、下划线
        matches(regex, s);
    }

}
