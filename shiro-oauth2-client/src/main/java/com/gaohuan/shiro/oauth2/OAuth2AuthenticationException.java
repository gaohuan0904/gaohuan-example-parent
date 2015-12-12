package com.gaohuan.shiro.oauth2;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by huan on 2015/12/12.
 */
public class OAuth2AuthenticationException extends AuthenticationException {

    public OAuth2AuthenticationException(Throwable cause) {
        super(cause);
    }
}
