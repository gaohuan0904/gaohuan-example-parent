package com.gaohuan.shiro.service;

import com.gaohuan.shiro.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 密码辅助类
 * Created by gh on 2015/12/8.
 */
@Service
public class PasswordHelper {

    private static final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Value("${password.algorithmName}")
    private String algorithmName = "md5";

    @Value("${password.hashIterations}")
    private Integer hashIterations = 2;

    public static RandomNumberGenerator getRandomNumberGenerator() {
        return randomNumberGenerator;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }


    /**
     * 加密密码
     * @param user
     */
    public void encryptPassword(User user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,//摘要算法
                user.getPassword(),//要散列的密码
                user.getSalt(),//盐
                hashIterations//散列次数
        ).toHex();
        user.setPassword(newPassword);
    }

}
