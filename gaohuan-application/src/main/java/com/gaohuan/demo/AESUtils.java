package com.gaohuan.demo;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;


public class AESUtils {

    /**
     * AES 密钥
     */
    public static final String SECRETKEY = "1234567890123456";

    /**
     * @param @param  content
     * @param @param  password
     * @param @return
     * @return byte[]
     * @Description: 加密
     */
    private static byte[] encrypt(String content, String password) {
        try {
            byte[] raw = password.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
            return encrypted;
        } catch (Exception e) {
        }
        return null;
    }

    private static byte[] decrypt(byte[] content, String password) {
        try {
            byte[] raw = password.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
        }
        return null;
    }

    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];
            for (int i = 0; i < hexStr.length() / 2; i++) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1),
                        16);
                int low = Integer.parseInt(
                        hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte) (high * 16 + low);
            }
            return result;
        }
    }

    public static String encAESCode(String content, String passcode) {
        byte[] encryptResult = encrypt(content, passcode);
        String encryptResultStr = parseByte2HexStr(encryptResult);
        return encryptResultStr;
    }

    public static String desAESCode(String content, String passcode) throws UnsupportedEncodingException {
        byte[] decryptFrom = parseHexStr2Byte(content);
        byte[] decryptResult = decrypt(decryptFrom, passcode);
        String decryptString = new String(decryptResult, "UTF-8");
        return decryptString;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String jsondata = " {code:'LD160428100023'}";
        String transdata = encAESCode(jsondata, SECRETKEY);
        System.out.println(transdata);
        String responseData = "7670D6CF519F353BCDB7AF59B32CC49AA3688E960DACEC5027367EC75A93415A82FA7975F45888B3572AD0C6053B3BE718324F132C7960E68D30BC9C1E50014CB183EF756C004D1090E31F1ED915E312CD1785198241FEBA9E717B3CB131F31D6D2ED18FBAD4D03779A8A1F497D836071D048A2861CBECDAEA172CC7B510DC8A2D843F5EF9158F75BD3563AF4B354C3117BE58255148A085DD4A10056637DD833EA2969DA2ED46BD1F5FD3E16CA4F375336DD237C456F9D4640D03DC3C4CE328B6D6FA00693A869D477E264841375927BA3CC66BB374D3A372CCCDFD59105A9954B1951FD12670E3DD539AC8F242E010CFD1C953C2DD17124E5F01CD8BB26FE29168AF886FB602F81F26FF0634EA24031DC7270BE082547BDD04B7979E3ABE62185BC9EDBD8F8E28168C5096BE70AABB1B393110B1DE1CE3E17AF33F20CEA7C69F92757D9281282E6C4C2B26DAF2CB54A287DF366BA0536C09AE25CBDE5AF1D9C206D770C1FDA88CBA352DF31FDB7D52C1E08F2285CE764F33894331ADB25EE6DC9F728119293C9F1588BCFA5D9EB1206C3FED125DF8327E25A1A76B4F44C942359B92660B736BB1EE238989DCD174B0103960A5CFFFF632AD416DDFE5D9E16EDBBBDF0139B644BB6BC1F8D18F13FF8108035A7FBF2A1F3E1A9316217F9A37CB";

        transdata = desAESCode(responseData, SECRETKEY);
        System.out.println(transdata);

    }
}
