package com.gaohuan.mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gh on 2016/1/14.
 */
public class EmailUtil {

    public static final Map<String, String> signMap = new HashMap<String, String>() {{
        put("qq.com", "http://www.mail.qq.com");
        put("163.com", "http://mail.163.com");
    }};

    /**
     * 发送邮件
     *
     * @param subject
     * @param msg
     * @param toMail
     * @throws EmailException
     */
    public static void sendEmail(String subject, String msg, String toMail) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.163.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("gaohuan0904@163.com", "gao13033868554"));
        email.setSSLOnConnect(true);
        email.setFrom("gaohuan0904@163.com");
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(toMail);
        email.setCharset(StandardCharsets.UTF_8.name());
        email.send();
    }

    public static void main(String[] args) {
        try {
            sendEmail("test", "高欢", "gaohuan0904@163.com");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
