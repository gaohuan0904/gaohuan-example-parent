package com.gaohuan.security;

import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * Created by gh on 2016/1/16.
 */
public class MD5Test {

    public static void main(String[] args) throws Exception {

        String path = "D:\\intellj_project\\my-example\\application-example\\src\\main\\java\\com\\gaohuan\\security\\MD5Test.java";
        FileInputStream fis = new FileInputStream(new File(path));
        DigestInputStream dis = new DigestInputStream(fis, MessageDigest.getInstance("MD5"));
        int buf = 1024;
        byte[] buffer = new byte[buf];
        int read = dis.read(buffer, 0, buf);
        while (read > -1) {
            read = dis.read(buffer, 0, buf);
        }
        //关闭流
        dis.close();
        //获得messagedigest
        MessageDigest md = dis.getMessageDigest();
        //只要处理
        byte[] b = md.digest();
        String md5hex = Hex.encodeHexString(b);
        System.out.println(md5hex);


    }
}
