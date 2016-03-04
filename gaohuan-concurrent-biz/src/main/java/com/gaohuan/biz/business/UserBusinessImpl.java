package com.gaohuan.biz.business;

import com.gaohuan.biz.common.BusinessException;
import com.gaohuan.biz.common.BusinessResult;
import com.gaohuan.biz.entity.User;
import com.gaohuan.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by gh on 2016/3/4 0004.
 */
@Service("userBusiness")
public class UserBusinessImpl implements UserBusiness {
    @Autowired
    private UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 用户注册
     *
     * @param user
     */
    public BusinessResult<String> signIn(User user) {
        BusinessResult<String> businessResult = new BusinessResult<>();
        try {
            if (user == null) {
                throw new BusinessException("注册参数不能为空!");
            }
//            redisTemplate.opsForValue().setIfAbsent("")


            businessResult.setResult("注册成功");
        } catch (Exception e) {
            businessResult.setSuccess(false);
            businessResult.setMessage("[用户:" + user + "],注册失败");
        }

        return businessResult;
    }
}
