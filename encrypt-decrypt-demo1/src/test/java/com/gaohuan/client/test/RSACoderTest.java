package com.gaohuan.client.test;

import com.gaohuan.cipher.util.RSACoder;
import org.apache.commons.codec.binary.Base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

/**
 * Created by gh on 2016/1/18.
 */
public class RSACoderTest {

    private static byte[] publicKey;

    private static byte[] privateKey;

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        Map<String, Object> keyMap = RSACoder.initKey();
        publicKey = RSACoder.getPublicKey(keyMap);
        privateKey = RSACoder.getPrivateKey(keyMap);

        String inputStr = "RSA数字签名";
        byte[] data = inputStr.getBytes();
        //产生签名
        byte[] sign = RSACoder.sign(data, privateKey);

        System.out.println("sign=" + Base64.encodeBase64String(sign));

        //验证签名
        boolean status = RSACoder.verify(data, publicKey, sign);
        System.out.println("status=" + status);
        //验证
    }
}
