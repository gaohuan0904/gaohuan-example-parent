package com.gaohuan.security;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

/**
 * PBE安全编码组件
 */
public class PBECoder {
    /*
        算法
     */
    public static final String ALGORITHM = "PBEWITHMD5andDES";
    /*
        迭代次数
     */
    public static final int INTERATION_COUNT = 100;

    /**
     * 盐初始化
     *
     * @return
     */
    public static byte[] initSalt() {
        //实例化安全随机数
        SecureRandom random = new SecureRandom();
        //产出盐
        return random.generateSeed(8);
    }

    /**
     * 转换密钥
     *
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static Key toKey(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //密钥材料转换
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        //实力化
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        //生成密钥
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        return secretKey;
    }

    /**
     * 加密
     *
     * @param data
     * @param password
     * @param salt
     * @return
     */
    public static byte[] encrypt(byte[] data, String password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        //转换密钥
        Key key = toKey(password);
        //实例化PBE参数材料
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, INTERATION_COUNT);

        //实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //初始化
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
        //执行操作
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data
     * @param password
     * @param salt
     * @return
     */
    public static byte[] decrypt(byte[] data, String password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Key key = toKey(password);
        //实例化PBE参数材料
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, INTERATION_COUNT);
        //实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //初始化
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
        //执行操作
        return cipher.doFinal(data);
    }

}
