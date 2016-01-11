package com.gaohuan.main;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

/**
 * Created by gh on 2016/1/7.
 */
public class ObjectMapperTest {
    public static void main(String[] args) throws JsonProcessingException {

        String str = idGen();
        System.out.println(str);

    }

    public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }


    private static String renameFile(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return UUID.randomUUID().toString().replaceAll("-", "") + fileName.substring(pos);

    }

    public static String idGen() {
        StringBuffer sb = new StringBuffer();
        sb.append(System.currentTimeMillis());
//        sb.reverse();
        return "HBA01" + sb.toString();

    }


}

