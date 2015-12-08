package com.gaohuan.shiro.web;

import com.gaohuan.shiro.service.OauthService;
import com.gaohuan.shiro.util.Constants;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gh on 2015/12/8.
 */
@RestController
public class UserInfoController {

    @Autowired
    private OauthService oauthService;

    @RequestMapping("/userInfo")
    public HttpEntity userInfo(HttpServletRequest request) throws OAuthProblemException, OAuthSystemException {
        //构建Oauth资源请求
        OAuthAccessResourceRequest resourceRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);

        //获取accessToken
        String accessToken = resourceRequest.getAccessToken();

        //验证accessToken
        if (!oauthService.checkAccessToken(accessToken)) {
            //构建错误返回消息
            OAuthResponse oAuthResponse = OAuthResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                    .setRealm(Constants.RESOURCE_SERVER_REALM)
                    .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
                    .buildHeaderMessage();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(OAuth.HeaderType.WWW_AUTHENTICATE, oAuthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
            return new ResponseEntity(httpHeaders, HttpStatus.UNAUTHORIZED);
        }
        //返回用户名
        return new ResponseEntity(oauthService.getUsernameByAccessToken(accessToken), HttpStatus.OK);
    }
}
