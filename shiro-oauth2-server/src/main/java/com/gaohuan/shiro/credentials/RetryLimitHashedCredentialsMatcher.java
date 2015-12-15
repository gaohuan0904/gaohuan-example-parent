package com.gaohuan.shiro.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gh on 2015/12/7.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        AtomicInteger count = passwordRetryCache.get(username);
        //第一次登陆
        if (count == null) {
            count = new AtomicInteger(0);
            passwordRetryCache.put(username, count);
        }
        if (count.getAndIncrement() > 5) {
            throw new ExcessiveAttemptsException();
        }

        boolean doMatcher = super.doCredentialsMatch(token, info);
        if (doMatcher) {
            //匹配成功移除登陆次数记录
            passwordRetryCache.remove(username);
        }
        return doMatcher;
    }
}
