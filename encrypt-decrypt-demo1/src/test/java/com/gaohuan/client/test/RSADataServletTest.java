package com.gaohuan.client.test;

import com.gaohuan.cipher.util.AESCoder;
import com.gaohuan.cipher.util.HttpUtils;
import com.gaohuan.cipher.util.RSACoder;
import org.apache.commons.codec.DecoderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by gh on 2016/1/18.
 */
public class RSADataServletTest {

    private static final String PUBLIC_KEY = "305c300d06092a864886f70d0101010500034b0030480241008bdbbb281d701a7f5e117dc1651718b85af76b6ca689c2dacc199401c809980b6aa1e94d95aea29d56eda2c90ad08ac3532319e18991fae5fcb61e5ce488549b0203010001";

    private static final String url = "http://localhost:8080/RSADataServelt";

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        //密钥
        String secretKey = AESCoder.initKeyString();
        System.out.println("发送的数据:" + secretKey);
        byte[] output = HttpUtils.postRequest(url, RSACoder.encryptByPublicKey(secretKey.getBytes(), PUBLIC_KEY));

        //使用AES算法对数据解密
        String data = new String(AESCoder.decrypt(output, secretKey));

        System.out.println("接收的数据:" + data);
    }


}
