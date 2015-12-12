package com.gaohuan.shiro.oauth2;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.concurrent.SubjectAwareExecutor;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by huan on 2015/12/12.
 */
public class OAuth2AuthenticationFilter extends AuthenticatingFilter {

    private String authcCodeParam = "code";

    private String clientId;

    private String redirectUrl;

    private String responseType = "code";

    private String fialureUrl;

    public String getAuthcCodeParam() {
        return authcCodeParam;
    }

    public void setAuthcCodeParam(String authcCodeParam) {
        this.authcCodeParam = authcCodeParam;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getFialureUrl() {
        return fialureUrl;
    }

    public void setFialureUrl(String fialureUrl) {
        this.fialureUrl = fialureUrl;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String code = httpServletRequest.getParameter(authcCodeParam);
        return new OAuth2Token(code);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String error = request.getParameter("error");
        String errorDescription = request.getParameter("error_description");
        if (!StringUtils.isEmpty(error)) {
            WebUtils.issueRedirect(request, response, fialureUrl + "?error=" + error + "error_description=" + errorDescription);
            return false;
        }
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated()) {
            if (StringUtils.isEmpty(request.getParameter(authcCodeParam))) {
                saveRequestAndRedirectToLogin(request, response);
                return false;
            }
        }
        return executeLogin(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        issueSuccessRedirect(request, response);
        return false;

    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        if (subject.isAuthenticated() || subject.isRemembered()) {
            try {
                issueSuccessRedirect(request, response);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            try {
                WebUtils.issueRedirect(request, response, fialureUrl);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }
}
