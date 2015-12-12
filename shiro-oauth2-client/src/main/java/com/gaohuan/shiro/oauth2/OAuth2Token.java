package com.gaohuan.shiro.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by huan on 2015/12/12.
 */
public class OAuth2Token implements AuthenticationToken{

    private String authCode;

    private String principal;


    public OAuth2Token(String authCode) {
        this.authCode = authCode;
    }

    public String getPrincipal() {
        return null;
    }

    public Object getCredentials() {
        return authCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

}
