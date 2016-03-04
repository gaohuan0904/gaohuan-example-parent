package com.gaohuan.security;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Date;

/**
 * Created by gh on 2016/1/11.
 */
public class Sample1 {

    public static void main(String[] args) {
//        printProviders();
//        test1();
//        test2();
//        test3();
        test4();
    }

    public static void printProviders() {
        Provider[] providers = Security.getProviders();
        for (Provider p : providers) {
            // System.out.println(p.getInfo());
            System.out.println(p.getName());
        }
    }

    public static void test1() {
        byte[] data = "Data Signature".getBytes();
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");

            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            Signature signature = Signature.getInstance(keyPairGenerator.getAlgorithm());
            signature.initSign(keyPair.getPrivate());

            //更新
            signature.update(data);
            //签名
            byte[] sign = signature.sign();
            System.out.println(sign);
//            System.out.println(new String(data, StandardCharsets.UTF_8));

            //公钥验证

            signature.initVerify(keyPair.getPublic());
            signature.update(data);
            boolean status = signature.verify(sign);
            System.out.println("status:" + status);

            System.out.println(keyPair.getPrivate().getEncoded());
            System.out.println(keyPair.getPublic().toString());


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {

        try {
            byte[] data = "Data signature".getBytes();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            Signature signature = Signature.getInstance(keyPairGenerator.getAlgorithm());

            SignedObject signedObject = new SignedObject(data, keyPair.getPrivate(), signature);

            byte[] sign = signedObject.getSignature();

            //验证签名

            boolean status = signedObject.verify(keyPair.getPublic(), signature);

            System.out.println("test2:" + status);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static void test3() {

        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
            CertPath certPath = certificateFactory.generateCertPath(new FileInputStream("D:\\x.cer"));
            Timestamp t = new Timestamp(new Date(), certPath);
            CodeSigner cs = new CodeSigner(certPath, t);

            boolean status = cs.equals(new CodeSigner(certPath, t));
            System.out.println(status);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void test4() {

        byte[] input = "MAC".getBytes();
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
            SecretKey secretKey = keyGenerator.generateKey();

            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            byte[] output = mac.doFinal(input);
            valueToString(output);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static void valueToString(byte[] bytes) {
        System.out.println(new String(bytes));
    }


}
