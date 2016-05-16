package com.gaohuan.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by gh on 2016/1/22.
 */
public class MyTest {

    public enum DirPathType {
        PHOTO,
        IMAGE,
        SYSIMAGE;

    }

    public static void main(String[] args) {
        /*
        String targetPath = "http:\\adfa\\adsfa\\adfa\\adfa/1234/1234/1234";
        targetPath = targetPath.replaceAll("\\\\", "/");
        System.out.println(targetPath);

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println();
        System.out.println(DigestUtils.md5Hex(uuid));
        System.out.println(RandomStringUtils.randomAlphanumeric(6));
        */
        /*
        Object obj = null;
        System.out.println(obj);
        obj = (obj == null) ? new Object() : obj;
        System.out.println(obj);
        */
        /*
        BigDecimal newPrice=new BigDecimal("1.11");
        BigDecimal cartPrice=new BigDecimal("1.110");
        System.out.println(newPrice.compareTo(cartPrice));
        */
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("2");

        List newList=new ArrayList<>(new HashSet<>(list));
        System.out.println(newList.toString());

    }
}
