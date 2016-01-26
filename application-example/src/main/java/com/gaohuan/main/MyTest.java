package com.gaohuan.main;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

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

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println();
        System.out.println(DigestUtils.md5Hex(uuid));
        System.out.println(RandomStringUtils.randomAlphanumeric(6));

    }
}
