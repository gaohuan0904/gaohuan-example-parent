package com.gaohuan.security;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/**
 * http工具
 */
public abstract class HttpUtils {

    public static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static final String CHARACTER_ENCODING = "UTF-8";

    public static final String METHOD_POST = "POST";

    public static final String CONTENT_TYPE = "Content-Type";

    /**
     * 打印数据
     *
     * @param response
     * @param data
     * @throws IOException
     */
    public static void responseWriter(HttpServletResponse response, byte[] data) throws IOException {
        if (data != null) {
            response.setContentLength(data.length);
            DataOutputStream out = new DataOutputStream(response.getOutputStream());
            out.write(data);
            out.flush();
            out.close();
        }
    }

    /**
     * 从请求中读取字节流
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] requestRead(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        byte[] data = null;
        if (contentLength > 0) {
            data = new byte[contentLength];
            InputStream is = request.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            dis.read(data);
            dis.close();
        }
        return data;
    }

    /**
     * 以post方式向指定地址发起请求，取得返回数据
     *
     * @param urlString
     * @param requestData
     * @return
     */
    public static byte[] postRequest(String urlString, byte[] requestData) {
        Properties requestProperties = new Properties();
        requestProperties.setProperty(CONTENT_TYPE, "application/octet-stream;charset=" + CHARACTER_ENCODING);
        return postRequest(urlString, requestData, requestProperties);
    }

    /**
     * 以post方式向指定地址发起请求，取得返回数据
     *
     * @param urlString
     * @param requestData
     * @param requestProperties
     * @return
     */
    public static byte[] postRequest(String urlString, byte[] requestData, Properties requestProperties) {
        byte[] responseData = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            //设置参数
            if (requestProperties != null && requestProperties.size() > 0) {
                for (Map.Entry<Object, Object> entry : requestProperties.entrySet()) {
                    String key = String.valueOf(entry.getKey());
                    String value = String.valueOf(entry.getValue());
                    urlConnection.setRequestProperty(key, value);
                }
            }
            urlConnection.setRequestMethod(METHOD_POST);
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            //传输请求数据
            DataOutputStream dos = new DataOutputStream(urlConnection.getOutputStream());
            if (requestData != null) {
                dos.write(requestData);
            }

            dos.flush();
            dos.close();
            //请求结果处理
            DataInputStream dis = new DataInputStream(urlConnection.getInputStream());
            int length = urlConnection.getContentLength();
            if (length > 0) {
                responseData = new byte[length];
                dis.read(responseData);
            }
            dis.close();

        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                urlConnection = null;
            }
        }
        return responseData;
    }


}
