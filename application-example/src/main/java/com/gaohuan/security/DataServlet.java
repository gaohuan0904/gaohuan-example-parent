package com.gaohuan.security;

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
public class DataServlet extends HttpServlet {
    private static final long serialVersionUID = 1180105879573302264L;

    private static final Logger logger = LoggerFactory.getLogger(DataServlet.class);
    //密钥
    private static String key;

    private static final String KEY_PARAM = "key";
    /*
        http header 摘要参数名
     */
    private static final String HEAD_MD = "messageDigest";


    @Override
    public void init() throws ServletException {
        super.init();
        key = getInitParameter(KEY_PARAM);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //获得参数摘要信息
            String messageDigest = req.getHeader(HEAD_MD);
            //读取数据
            byte[] input = HttpUtils.requestRead(req);

            byte[] data = AESCoder.decrypt(input, key);
            logger.debug("收到请求数据:" + new String(data));
            //默认回复内容
            byte[] output = "".getBytes();
            //验证成功
            if (AESCoder.validation(data, messageDigest)) {
                output = "OK".getBytes();
            }
            //加密回复
            HttpUtils.responseWriter(resp, AESCoder.encrypt(output, key));

        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }
}


