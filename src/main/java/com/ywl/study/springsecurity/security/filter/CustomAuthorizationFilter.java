package com.ywl.study.springsecurity.security.filter;

import com.alibaba.fastjson.JSON;
import com.ywl.study.springsecurity.entity.FrameUserDetails;
import com.ywl.study.springsecurity.redis.RedisUtil;
import com.ywl.study.springsecurity.security.TokenConst;
import com.ywl.study.springsecurity.security.service.UserInfoService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//2
public class CustomAuthorizationFilter extends BasicAuthenticationFilter {
    private RedisUtil redisUtil;

    public CustomAuthorizationFilter(AuthenticationManager authenticationManager,RedisUtil redisUtil) {
        super(authenticationManager);
        this.redisUtil=redisUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // FOR CORS
        if (request.getMethod().equals("OPTIONS")) {
            List<SimpleGrantedAuthority> authSet = new ArrayList<>();
            authSet.add(new SimpleGrantedAuthority(UserInfoService.ROLE_ADMIN));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("SYSTEM$OPTIONS$CORS", null, authSet));
            super.doFilterInternal(request, response, chain);
            return;
        }
        String tokenHeader = request.getHeader(TokenConst.TOKEN_HEADER);
        if (tokenHeader == null || "".equals(tokenHeader)) tokenHeader = request.getParameter(TokenConst.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(TokenConst.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // 如果请求头中有token，则进行解析，并且设置认证信息
        FrameUserDetails frameUserDetails = getFrameUserDetails(tokenHeader);
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(getFrameUserDetails(tokenHeader)));
        request.setAttribute(TokenConst.TOKEN_USERNAME, frameUserDetails.getUserid() + "[" + frameUserDetails.getUsername() + "]");
        super.doFilterInternal(request, response, chain);
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(FrameUserDetails user) {
        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
        }
        return null;
    }

    private FrameUserDetails getFrameUserDetails(String tokenHeader) {
        String token = tokenHeader.replace(TokenConst.TOKEN_PREFIX, "");
        String jsonStr = (String) redisUtil.get(TokenConst.TOKEN_MARK + token);
        return JSON.parseObject(jsonStr, FrameUserDetails.class);
    }
}
