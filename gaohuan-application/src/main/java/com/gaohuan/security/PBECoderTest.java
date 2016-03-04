package com.gaohuan.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by gh on 2016/1/16.
 */
public class PBECoderTest {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        String inputStr = "PBE";
        System.out.println("原文：" + inputStr);
        byte[] input = inputStr.getBytes();
        String pwd = "gaohuan0904@163.com";
        System.out.println("密码：" + pwd);
        //初始化验
        byte[] salt = PBECoder.initSalt();
        System.out.println("盐：" + Base64.encodeBase64String(salt));
        //加密
        byte[] data = PBECoder.encrypt(input, pwd, salt);
        System.out.println("加密后：" + Base64.encodeBase64String(data));
        //解密
        byte[] output = PBECoder.decrypt(data, pwd, salt);
        System.out.println("解密后：" + new String(output));

    }
}
