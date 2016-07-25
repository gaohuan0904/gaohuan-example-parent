package com.gaohuan.shiro.realm;


import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by acer on 2016/7/25.
 */
public class CustomRealm implements Realm {
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
        if (!"zhang".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username, password, getName());

    }
}
