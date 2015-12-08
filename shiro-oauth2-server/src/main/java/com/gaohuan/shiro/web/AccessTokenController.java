package com.gaohuan.shiro.web;

import com.gaohuan.shiro.service.OauthService;
import com.gaohuan.shiro.service.UserService;
import com.gaohuan.shiro.util.Constants;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gh on 2015/12/8.
 */
@Controller
public class AccessTokenController {

    @Autowired
    private OauthService oauthService;

    @Autowired
    private UserService userService;

    public HttpEntity token(HttpServletRequest request) throws OAuthSystemException {
        try {
            OAuthTokenRequest oAuthTokenRequest = new OAuthTokenRequest(request);
            //检查客户端id
            if (!oauthService.checkClientId(oAuthTokenRequest.getClientId())) {
                throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST, Constants.INVALID_CLIENT_DESCRIPTION);
            }
            //检查客户端安全key
            if (!oauthService.checkClientSecret(oAuthTokenRequest.getClientSecret())) {
                throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST, Constants.INVALID_CLIENT_DESCRIPTION);
            }
            String authCode = oAuthTokenRequest.getParam(OAuth.OAUTH_CODE);
            //检查验证类型
            if (oAuthTokenRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equalsIgnoreCase(GrantType.AUTHORIZATION_CODE.toString())) {
                if (!oauthService.checkAuthCode(authCode)) {
                    throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST, Constants.INVALID_AUTHCODE_DESCRIPTION);
                }
            }
            //生成Access Token

            OAuthIssuer oAuthIssuer = new OAuthIssuerImpl(new MD5Generator());
            final String accessToken = oAuthIssuer.accessToken();
            oauthService.addAccessToken(accessToken, oAuthTokenRequest.getUsername());

            //生成Oauth响应
            OAuthResponse oAuthResponse = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken)
                    .setExpiresIn(String.valueOf(oauthService.getExpireIn()))
                    .buildJSONMessage();

            return new ResponseEntity(oAuthResponse.getBody(), HttpStatus.valueOf(oAuthResponse.getResponseStatus()));

        } catch (Exception e) {
            return errorResponse(e);
        }

    }

    private HttpEntity errorResponse(Exception e) throws OAuthSystemException {
        //构建OAuth错误响应
        OAuthResponse oAuthResponse = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                .setRealm(Constants.RESOURCE_SERVER_REALM)
                .error(OAuthProblemException.error(e.getMessage()))
                .buildJSONMessage();

        return new ResponseEntity(oAuthResponse.getBody(), HttpStatus.valueOf(oAuthResponse.getResponseStatus()));
    }

}
