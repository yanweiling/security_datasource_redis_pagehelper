package com.ywl.study.springsecurity.security.filter;

import com.ywl.study.springsecurity.mapper.QxUserDao;
import com.ywl.study.springsecurity.entity.QxGnmk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private Map<String,String> authenCache=new HashMap<>();
    @Autowired
    private QxUserDao qxUserDao;

    /**
     * Spring Security会将doFilter传进来的request，response和FitlerChain保存到FilterInvocation中
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi= (FilterInvocation) object;
        Collection<ConfigAttribute> configAttributes=new ArrayList<>();
        // FOR CORS
        if (fi.getHttpRequest().getMethod().equals("OPTIONS")) {
            return configAttributes;
        }
        String requestUrl=fi.getRequestUrl();
        log.debug("requestUrl:" + requestUrl);
        if (this.authenCache == null || this.authenCache.size() <= 0) {
            loadResourceDefine();
        }
        if (this.authenCache != null && this.authenCache.size() > 0) {
            Set<String> keySet = this.authenCache.keySet();
            if (requestUrl.startsWith("/"))
                requestUrl = requestUrl.substring(1);
            for (String key : keySet) {
                String gnzyUrl = this.authenCache.get(key);
                if (compareUrl(gnzyUrl, requestUrl)) {
                    ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + key);
                    configAttributes.add(configAttribute);
                }
            }
        }
        // 当系统中没配资源权限url，用户在访问这个资源的情况下，返回null表示放行 ，
        // 如果当系统分配了资源url,但是这个用户隶属的角色没有则提示用户无权访问这个页面
        // 返回值到CustomAccessDecisionManager类decide方法进行判断，是否通过。
        return configAttributes;
    }

    /**
     * 加载权限资源信息
     */
    public synchronized void loadResourceDefine() {
        List<QxGnmk> allGnmk = this.qxUserDao.findAllGnmk();
        for (QxGnmk gnmk : allGnmk) {
            // TODO url binding
            this.authenCache.put(gnmk.getGnmk_dm(), gnmk.getGnmk_dm());
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * 判断功能模块URL与请求URL是否匹配
     *
     * @param gnmkUrl    功能模块URL
     * @param requestUrl 请求URL
     * @return 匹配结果
     */
    public static boolean compareUrl(String gnmkUrl, String requestUrl) {
        boolean flag = false;
        String[] gs = gnmkUrl.split("\\?");
        String[] rs = requestUrl.split("\\?");
        if (rs.length == 1 && gs.length > 0) {
            if (gs[0].endsWith(requestUrl)) {
                flag = true;
            }
        } else if (rs.length > 1 && gs.length == 1) {
            if (gnmkUrl.endsWith(rs[0]))
                flag = true;
        } else if (rs.length > 1 && gs.length > 1) {
            if (gs[0].endsWith(rs[0]) && rs[1].startsWith(gs[1]))
                flag = true;
        }
        return flag;
    }
}
