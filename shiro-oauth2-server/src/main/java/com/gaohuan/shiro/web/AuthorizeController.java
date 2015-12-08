package com.gaohuan.shiro.web;

import com.gaohuan.shiro.service.ClientService;
import com.gaohuan.shiro.service.OauthService;
import com.gaohuan.shiro.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by gh on 2015/12/8.
 */
@Controller
public class AuthorizeController {

    @Autowired
    private OauthService oauthService;

    @Autowired
    private ClientService clientService;


    @RequestMapping(value = "/authorize")
    public Object authorize(Model model, HttpServletRequest request) throws OAuthProblemException, OAuthSystemException, URISyntaxException {

        //构建授权请求
        OAuthAuthzRequest oAuthAuthzRequest = new OAuthAuthzRequest(request);
        try {
            String clientId = oAuthAuthzRequest.getClientId();

            //检查clientId是否正确
            if (!oauthService.checkClientId(clientId)) {
                throw OAuthProblemException.error(Constants.INVALID_CLIENT_DESCRIPTION);
            }
            if (StringUtils.isEmpty(oAuthAuthzRequest.getRedirectURI())) {
                throw OAuthProblemException.error(Constants.INVALID_REDIRECTURI_DESCRIPTION);
            }

            Subject subject = SecurityUtils.getSubject();
            //若果用户没有登录，跳转到登录页面
            if (!subject.isAuthenticated()) {//没有登录
                if (!login(subject, request)) {//登录失败
                    model.addAttribute("client", clientService.findByClientId(clientId));
                    return "oauth2login";
                }
            }
            String username = (String) subject.getPrincipal();
            //生成授权码
            String authorizeCode = null;
            String responseType = oAuthAuthzRequest.getParam(ResponseType.CODE.toString());
            if (responseType.equals(ResponseType.CODE.toString())) {
                OAuthIssuer oAuthIssuer = new OAuthIssuerImpl(new MD5Generator());
                authorizeCode = oAuthIssuer.authorizationCode();
                oauthService.addAuthCode(authorizeCode, username);
            }

            //构建OAuth响应
            OAuthResponse oAuthResponse = OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND)//重定向状态
                    .location(oAuthAuthzRequest.getRedirectURI())//客户端重定向地址
                    .buildQueryMessage();

            //根据OauthoResponse构建http响应
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(new URI(oAuthResponse.getLocationUri()));

            return new ResponseEntity(httpHeaders, HttpStatus.valueOf(oAuthResponse.getResponseStatus()));
        } catch (Exception e) {
            return errorResponse(oAuthAuthzRequest, e);
        }
    }


    /**
     * 验证登录
     * @param subject
     * @param request
     * @return
     */
    public boolean login(Subject subject, HttpServletRequest request) {
        //不支持get
        if (request.getMethod().equalsIgnoreCase("get")) {
            return false;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
            return true;
        } catch (Exception e) {
            request.setAttribute("error", "登录失败");
            return false;
        }
    }

    /**
     * 处理错误
     * @param request
     * @param e
     * @return
     * @throws OAuthSystemException
     */
    private ResponseEntity errorResponse(OAuthAuthzRequest request, Exception e) throws OAuthSystemException {

        //构建OAuth响应
        OAuthResponse oAuthResponse = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                .setRealm(Constants.RESOURCE_SERVER_REALM)
                .error(OAuthProblemException.error(e.getMessage()))
                .buildJSONMessage();

        return new ResponseEntity(oAuthResponse.getBody(), HttpStatus.valueOf(oAuthResponse.getResponseStatus()));

    }
}
