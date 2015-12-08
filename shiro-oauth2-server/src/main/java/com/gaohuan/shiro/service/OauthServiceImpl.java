package com.gaohuan.shiro.service;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by gh on 2015/12/8.
 */
@Service
public class OauthServiceImpl implements OauthService {
    public static final String CODE_CACHE_NAME = "codeCache";

    @Autowired
    private ClientService clientService;

    @Autowired
    private CacheManager cacheManager;

    private Cache<String, String> cache;

    @PostConstruct
    public void init() {
        this.cache = cacheManager.getCache(CODE_CACHE_NAME);
    }

    public void addAuthCode(String authCode, String username) {
        cache.put(authCode, username);

    }

    public void addAccessToken(String accessToken, String username) {
        cache.put(accessToken, username);
    }

    public boolean checkAuthCode(String authCode) {
        return cache.get(authCode) != null;
    }

    public boolean checkAccessToken(String accessToken) {
        return cache.get(accessToken) != null;
    }

    public String getUsernameByAuthCode(String authCode) {
        return cache.get(authCode);
    }

    public String getUsernameByAccessToken(String accessToken) {
        return cache.get(accessToken);
    }

    public long getExpireIn() {
        return 3600l;
    }

    public boolean checkClientId(String clientId) {
        return clientService.findByClientId(clientId) != null;
    }

    public boolean checkClientSecret(String clientScret) {
        return clientService.findByClientSecret(clientScret) != null;
    }
}
