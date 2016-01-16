package com.gaohuan.client.test;

import com.gaohuan.cipher.util.AESCoder;
import com.gaohuan.cipher.util.HttpUtils;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 测试加密解密传输
 */
public class DataServletTest {

    private static final String key = "XzcSW/UJvGd9hJBX6rkGGGq+vbo3f80YoUMTEZKeSIM=";

    private static final String requestUrl = "http://localhost:8080/DataServlet";

    public static void main(String[] args) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {

        Map<String, String> xmlMap = new HashMap<String, String>();
        xmlMap.put("name", "gaohuan");
        xmlMap.put("age", "25");
        byte[] data = buildXml(xmlMap).getBytes();

        //参数
        Properties requestProperties = new Properties();
        requestProperties.put("messageDigest", AESCoder.shaHex(data));
        //加密并发送数据
        byte[] input = HttpUtils.postRequest(requestUrl, AESCoder.encrypt(data, key), requestProperties);
        //解密
        input = AESCoder.decrypt(input, key);
        System.out.println(new String(input));
    }

    /**
     * 构建xml
     *
     * @param xmlMap
     * @return
     */
    public static String buildXml(Map<String, String> xmlMap) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlBuilder.append("<xml>");
        for (String key : xmlMap.keySet()) {
            String value = xmlMap.get(key);
            xmlBuilder.append("<" + key + ">").append(value).append("</" + key + ">");
        }
        xmlBuilder.append("</xml>");

        return formatXml(xmlBuilder.toString());
    }

    /**
     * 格式化xml
     *
     * @param xml
     * @return
     */
    public static String formatXml(String xml) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(new InputSource(new StringReader(xml)));
            OutputFormat outputFormat = new OutputFormat(document);
            outputFormat.setLineWidth(65);
            outputFormat.setIndenting(true);
            outputFormat.setIndent(2);
            Writer writer = new StringWriter();
            XMLSerializer xmlSerializer = new XMLSerializer(writer, outputFormat);
            xmlSerializer.serialize(document);
            return writer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
