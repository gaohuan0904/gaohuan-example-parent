package com.gaohuan.biz.utils;

/**
 * Created by huan on 2016/3/5.
 */
public class RedisConstant {
    /**
     * 用户注册标示key,防止重复注册
     */
    public static final String LOCK_SIGNIN_DUPLICATE_USERNAME_KEY = "lock:signIn:duplicate:username:";

    public static final String SIGNIN_SUCCESSFUL_USERNAME_KEY = "gaohuan:signIn:successful:username";
}
