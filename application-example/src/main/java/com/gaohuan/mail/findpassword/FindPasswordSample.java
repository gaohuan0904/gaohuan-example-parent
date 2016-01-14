package com.gaohuan.mail.findpassword;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.mail.EmailException;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gh on 2016/1/14.
 */
public class FindPasswordSample {

    public static final Map<String, String> storageMap = new ConcurrentHashMap<>();

    /**
     * @param username
     * @param mail
     * @param subject
     * @throws EmailException
     */
    public static void sendMail(String username, String mail, String subject) throws EmailException {
        EmailUtil.sendEmail(subject, getSendContent(username, mail), mail);

    }

    /**
     * 获取发送内容
     *
     * @param username
     * @param mail
     * @return
     */
    public static String getSendContent(String username, String mail) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(RandomStringUtils.random(10));
        strBuilder.append(username);
        strBuilder.append(mail);
        String key = DigestUtils.md5Hex(strBuilder.toString());
        String time = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss");

        strBuilder.setLength(0);
        strBuilder.append("亲爱的用户：<br/><br/>");
        strBuilder.append("点击以下链接设置新密码。<br/><br/>");
        strBuilder.append("<a href =\"http://www.域名.com/user/ReSetUserPassWord?key=" + key + "&time=" + time + "\">http://www.域名.com/user/ReSetUserPassWord?key=" + key + "&time=" + time + " </a><br/><br/>");
        strBuilder.append("(如果无法点击该URL链接地址，请将它复制并粘帖到浏览器的地址输入框，然后单击回车即可。)<br/><br/>");
        strBuilder.append("注意:请您在收到邮件<font style='color:red;font-weigth:border'>1小时内</font>使用，否则该链接将会失效。<br/><br/>");
        strBuilder.append("我们将一如既往、热忱的为您服务！<br/><br/>");

        System.out.println("key=" + key + " username=" + username + " mail=" + mail);
        storageMap.put(key, username);
        return strBuilder.toString();
    }

    private static String getMailSign(String mail) {
        return mail.substring(mail.lastIndexOf("@") + 1);
    }

    public String getMailAddr(String mail) {
        return EmailUtil.signMap.get(getMailSign(mail));
    }

    /**
     * 模拟重置密码操作
     *
     * @param key
     * @param time
     * @throws ParseException
     */
    public void resetPassword(String key, String time) throws ParseException {
        Date date = (Date) DateFormatUtils.ISO_DATETIME_FORMAT.parseObject(time);
        date = DateUtils.addHours(date, 1);
        if (storageMap.get(key) == null || date.compareTo(new Date()) < 0) {
            System.out.println("重置密码验证失败");
        }
        System.out.println(storageMap.get(key) + "更新密码成功");

    }

    public static void main(String[] args) {
        try {
            sendMail("gaohuan", "gaohuan0904@163.com", "重置密码");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}
