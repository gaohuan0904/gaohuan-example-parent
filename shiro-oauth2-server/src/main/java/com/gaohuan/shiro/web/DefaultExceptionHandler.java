package com.gaohuan.shiro.web;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gh on 2015/12/7.
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    /**
     * 默认异常处理
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(HttpServletRequest request, UnauthorizedException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.setViewName("unauthorized");
        return mav;
    }
}
