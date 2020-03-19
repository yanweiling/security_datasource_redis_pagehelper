package com.ywl.study.springsecurity.security;

import com.ywl.study.springsecurity.redis.RedisUtil;
import com.ywl.study.springsecurity.security.filter.CustomAuthenticationFilter;
import com.ywl.study.springsecurity.security.filter.CustomAuthorizationFilter;
import com.ywl.study.springsecurity.security.filter.CustomFilterSecurityInterceptor;
import com.ywl.study.springsecurity.security.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity//(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法安全设置
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserInfoService userInfoService;//用来根据用户名获取用户信息实体类
    @Autowired
    private CustomFilterSecurityInterceptor customFilterSecurityInterceptor;

    @Autowired
    private RedisUtil redisUtil;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userInfoService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/auth/**","/test/**").permitAll()
                //解决静态资源被拦截的问题
                .antMatchers("/**/*.js", "/**/*.css", "/**/*.jpg", "/**/*.png", "/**/*.html", "/**/*.woff", "/**/*.ico", "/**/*.gif").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        http.addFilterBefore(customFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        http.addFilter(new CustomAuthenticationFilter(authenticationManager(),redisUtil))
                .addFilter(new CustomAuthorizationFilter(authenticationManager(), redisUtil));
        //禁用缓存
        http.headers().cacheControl();
    }

    /**
     * 密码生成策略.
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                try {
                    return MD5PasswordAuthenticator.createPassword((String) rawPassword);// (String) rawPassword;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return MD5PasswordAuthenticator.authentaication((String) rawPassword, encodedPassword);
                //return encodedPassword.equals((String) rawPassword);
            }
        };
        //return new BCryptPasswordEncoder();   // 使用 BCrypt 加密
    }
}
