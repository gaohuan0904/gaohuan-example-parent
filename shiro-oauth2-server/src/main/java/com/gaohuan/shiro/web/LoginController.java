package com.gaohuan.shiro.web;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gh on 2015/12/8.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login" )
    public String showLoginForm(Model model, HttpServletRequest request) {

        String shiroExceptionClass = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        String error = null;
        if (AuthenticationException.class.getName().equals(shiroExceptionClass)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(shiroExceptionClass)) {
            error = "用户名/密码错误";
        } else if (shiroExceptionClass != null) {
            error = "其他错误:" + shiroExceptionClass;
        }
        model.addAttribute("error", error);

        return "login";
    }

}
