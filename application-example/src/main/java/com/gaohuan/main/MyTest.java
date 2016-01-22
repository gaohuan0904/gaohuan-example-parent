package com.gaohuan.main;

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
        String targetPath = "http:\\adfa\\adsfa\\adfa\\adfa/1234/1234/1234";
        targetPath = targetPath.replaceAll("\\\\", "/");
        System.out.println(targetPath);


    }
}
