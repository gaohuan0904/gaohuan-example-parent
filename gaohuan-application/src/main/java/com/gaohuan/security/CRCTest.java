package com.gaohuan.security;

import java.util.zip.CRC32;

/**
 * crc32算法
 */
public class CRCTest {

    public static void testCRC32() {
        String str = "测试crc-32";
        CRC32 crc32 = new CRC32();
        crc32.update(str.getBytes());
        String hex = Long.toHexString(crc32.getValue());
        System.out.println("原文：" + str);
        System.out.println("crc-32:" + hex);

    }

    public static void main(String[] args) {
        testCRC32();
    }


}
