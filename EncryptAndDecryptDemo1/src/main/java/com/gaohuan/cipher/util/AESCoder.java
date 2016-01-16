package com.gaohuan.cipher.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * AES安全编码组件
 */
public abstract class AESCoder {

    public static final String KEY_ALGORITHM = "AES";

//    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static Key toKey(byte[] key) {
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static byte[] initKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        kg.init(256);

        return kg.generateKey().getEncoded();
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String initKeyString() throws NoSuchAlgorithmException {
        return Base64.encodeBase64String(initKey());
    }

    /**
     * 获取密钥
     *
     * @param key
     * @return
     */
    public static byte[] getKey(String key) {
        return Base64.decodeBase64(key);
    }

    public static byte[] decrypt(byte[] data, String key) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(data, getKey(key));
    }

    public static byte[] encrypt(byte[] data, String key) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return encrypt(data, getKey(key));
    }

    /**
     * 摘要处理
     *
     * @param data
     * @return
     */
    public static String shaHex(byte[] data) {
        return DigestUtils.shaHex(data);
    }

    /**
     * 摘要处理
     *
     * @param data
     * @return
     */
    public static String md5Hex(byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    public static boolean validation(byte[] data, String messageDigest) {
        return messageDigest.equals(shaHex(data));
    }


}
