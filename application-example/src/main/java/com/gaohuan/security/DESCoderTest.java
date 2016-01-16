package com.gaohuan.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by gh on 2016/1/16.
 */
public class DESCoderTest {

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, InvalidKeySpecException, InvalidKeyException {
        String inputStr = "DES";
        System.out.println("原文：" + inputStr);
        byte[] inputData = inputStr.getBytes();
        //初始化密钥
        byte[] key = DESCoder.initKey();
        System.out.println("密钥：" + Base64.encodeBase64String(key));
        //加密
        inputData = DESCoder.encrypt(inputData, key);
        System.out.println("加密后:" + Base64.encodeBase64String(inputData));
        //解密
        byte[] outputData = DESCoder.decrypt(inputData, key);
        String outputStr = new String(outputData);
        System.out.println("解密后：" + outputStr);
    }
}
