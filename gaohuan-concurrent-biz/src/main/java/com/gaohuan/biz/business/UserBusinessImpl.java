package com.gaohuan.biz.business;

import com.gaohuan.biz.common.BusinessException;
import com.gaohuan.biz.common.BusinessResult;
import com.gaohuan.biz.entity.User;
import com.gaohuan.biz.service.UserService;
import com.gaohuan.biz.utils.RedisConstant;
import com.gaohuan.biz.utils.RedisLock;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

/**
 * 用户具体业务操作
 */
@Service("userBusiness")
public class UserBusinessImpl implements UserBusiness {
    public static final Logger logger = LoggerFactory.getLogger(UserBusinessImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    RedisTemplate redisTemplate;
    /**
     * 分布式锁
     */
    @Autowired
    private RedisLock redisLock;

    /**
     * 用户注册
     *
     * @param user
     */
    public BusinessResult<String> signIn(User user) {
        long start = System.currentTimeMillis();
        BusinessResult<String> businessResult = new BusinessResult<>();
        try {
            if (user == null || StringUtils.isBlank(user.getName())) {
                throw new BusinessException("注册参数不能为空!");
            }
            //通过缓存验证是否注册过
            SetOperations setOperations = redisTemplate.opsForSet();
            if (setOperations.isMember(RedisConstant.SIGNIN_SUCCESSFUL_USERNAME_KEY, user.getName())) {
                throw new BusinessException("【reids-1-check】-注册用户已存在!");
            }
            String lockKey = RedisConstant.LOCK_SIGNIN_DUPLICATE_USERNAME_KEY + user.getName();
            try {

                if (!redisLock.lock(lockKey)) {
                    throw new BusinessException("【redis】-获取锁失败!");
                }
                //获取到锁后再执行一次检查
                if (setOperations.isMember(RedisConstant.SIGNIN_SUCCESSFUL_USERNAME_KEY, user.getName())) {
                    throw new BusinessException("【reids-2-check】-注册用户已存在!");
                }
                //通过数据库验证是否注册过
                User findUser = userService.findByUsername(user.getName());
                if (findUser != null) {
                    //从数据存中查询到用户存在，说明缓存没起作用，更新缓存。
                    setOperations.add(RedisConstant.SIGNIN_SUCCESSFUL_USERNAME_KEY, user.getName());
                    throw new BusinessException("【db】-注册用户已存在!");
                }
                userService.save(user);
                //保存成功，加入缓存
                setOperations.add(RedisConstant.SIGNIN_SUCCESSFUL_USERNAME_KEY, user.getName());
                redisLock.unlock(lockKey);
            } catch (Exception e) {
                redisLock.unlock(lockKey);
                throw new BusinessException("操作失败:" + e.getMessage(), e);
            }
            logger.info("注册成功");
            businessResult.setResult("注册成功");
        } catch (Exception e) {
            businessResult.setSuccess(false);
            businessResult.setMessage("【用户:" + user + "】,注册失败:" + e.getMessage());
            logger.error("【用户:" + user + "】,注册失败:" + e.getMessage(), e);
        }
        logger.info("------------时间: " + (System.currentTimeMillis() - start) + " ");
        return businessResult;
    }
}
