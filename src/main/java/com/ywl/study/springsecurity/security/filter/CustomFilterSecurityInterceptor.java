package com.ywl.study.springsecurity.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

@Service
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
    @Autowired
    public void setCustomAccessDecisionManager(CustomAccessDecisionManager customAccessDecisionManager) {
        super.setAccessDecisionManager(customAccessDecisionManager);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    /**
     * fi里面有一个被拦截的url
     * 里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
     * 再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
     */
    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }
    @Override
    public void destroy() {

    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}
