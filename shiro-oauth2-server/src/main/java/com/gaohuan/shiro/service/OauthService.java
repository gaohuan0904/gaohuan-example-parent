package com.gaohuan.shiro.service;

/**
 * Created by gh on 2015/12/8.
 */
public interface OauthService {

    public void addAuthCode(String authCode, String username);

    public void addAccessToken(String accessToken, String username);

    public boolean checkAuthCode(String authCode);

    public boolean checkAccessToken(String accessToken);

    public String getUsernameByAuthCode(String authCode);

    public String getUsernameByAccessToken(String accessToken);

    long getExpireIn();

    public boolean checkClientId(String clientId);

    public boolean checkClientSecret(String clientScret);
}
