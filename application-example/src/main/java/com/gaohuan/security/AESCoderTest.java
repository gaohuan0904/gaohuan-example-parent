package com.gaohuan.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by gh on 2016/1/16.
 */
public class AESCoderTest {

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        String inputStr = "AES";
        byte[] inputData = inputStr.getBytes();
        System.out.println("原文：" + inputStr);

        byte[] key = AESCoder.initKey();
        System.out.println("密钥：" + Base64.encodeBase64String(key));

        inputData = AESCoder.encrypt(inputData, key);
        System.out.println("加密后：" + Base64.encodeBase64String(inputData));

        byte[] outputData = AESCoder.decrypt(inputData, key);

        System.out.println("解密后：" + new String(outputData));

    }
}
