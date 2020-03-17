package com.ywl.study.springsecurity.security.filter;

import com.alibaba.fastjson.JSON;
import com.ywl.study.springsecurity.entity.FrameUserDetails;
import com.ywl.study.springsecurity.redis.RedisUtil;
import com.ywl.study.springsecurity.security.TokenConst;
import com.ywl.study.springsecurity.util.GUIDGen;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//1
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private RedisUtil redisUtil;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, RedisUtil redisUtil) {
        this.authenticationManager = authenticationManager;
        this.redisUtil = redisUtil;
        this.setFilterProcessesUrl("/auth/login");//默认拦截的是/login
    }

    /*从前端获取用户名和密码，构造没有未校验过的token*/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>()));
    }
    //user:1BD730B7-EFF0-3BCA-C67A-851AA74A1CB0
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        FrameUserDetails userDetails = (FrameUserDetails) authResult.getPrincipal();
        String remember = request.getParameter("remember");
        String token = GUIDGen.getGUIDString();
        redisUtil.set(TokenConst.TOKEN_MARK + token, JSON.toJSONString(userDetails), Boolean.valueOf(remember) ? TokenConst.EXPIRATION_REMEMBER : TokenConst.EXPIRATION);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("token", TokenConst.TOKEN_PREFIX + token);
        Map authMap = new HashMap<String, Object>() {{
            put("code", 200);
            put("TOKEN", token);
            put("msg", "登录成功.");
            put("data", userDetails.getQxUser());
        }};
        response.getWriter().write(JSON.toJSONString(authMap));
        response.getWriter().flush();


        super.successfulAuthentication(request, response, chain, authResult);
    }

    /**
     *
     * spring.http.encoding.charset=UTF-8
     * spring.http.encoding.force=true
     * 如果没有这两个设置，response的编码格式是ISO-8859-1
     * 有这两个设置后response的编码格式就变成UTF-8
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"msg\":\"登录失败:" + failed.getMessage() + "\",\"code\":400}");
        response.getWriter().flush();
    }
}
