package com.gaohuan.shiro.realm;


import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by acer on 2016/7/25.
 */
public class CustomRealm implements Realm {

    public static final Map<String, String> principalMap = new HashMap<String, String>();

    static {
        principalMap.put("zhang", "123");
        principalMap.put("wang", "123");
    }

    @Override
    public String getName() {
        return "customRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        if (!principalMap.keySet().contains(username)) {
            throw new UnknownAccountException();
        }
        if (!principalMap.values().contains(password)) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username, password, getName());

    }
}
