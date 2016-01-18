package com.gaohuan.cipher.servlet;

import com.gaohuan.cipher.util.AESCoder;
import com.gaohuan.cipher.util.HttpUtils;
import com.gaohuan.cipher.util.RSACoder;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gh on 2016/1/16.
 */
public class RSADataServlet extends HttpServlet {
    private static final long serialVersionUID = -1127601450526064711L;

    private static final Logger logger = LoggerFactory.getLogger(RSADataServlet.class);
    //密钥
    private static String key;

    private static final String KEY_PARAM = "key";

    private static final String PUBLIC_KEY = "305c300d06092a864886f70d0101010500034b0030480241008bdbbb281d701a7f5e117dc1651718b85af76b6ca689c2dacc199401c809980b6aa1e94d95aea29d56eda2c90ad08ac3532319e18991fae5fcb61e5ce488549b0203010001";

    @Override
    public void init() throws ServletException {
        super.init();
        key = getInitParameter(KEY_PARAM);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //读取数据
            byte[] input = HttpUtils.requestRead(req);
            //接收客户端使用RSA加密的 AES key
            String inputKey = new String(RSACoder.decryptByPrivateKey(input, key));
            logger.debug("收到请求数据:\n" + inputKey);
            //默认回复内容
            byte[] output = "<xml><msg>success</msg></xml>".getBytes();
            //使用AES加密回复
            HttpUtils.responseWriter(resp, AESCoder.encrypt(output, inputKey));

        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }
}


