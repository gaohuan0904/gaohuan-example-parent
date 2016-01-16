package com.gaohuan.client.test;

import com.gaohuan.cipher.util.AESCoder;

import java.security.NoSuchAlgorithmException;

/**
 * 加密解决key生成
 */
public class KeyGen {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String key = AESCoder.initKeyString();
        System.out.println("key=" + key);
    }
}
