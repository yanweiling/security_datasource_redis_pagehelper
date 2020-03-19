package com.ywl.study.springsecurity.frame.aop;

import com.alibaba.fastjson.JSON;
import com.ywl.study.springsecurity.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 接口耗时统计
 */
@Aspect
@Component
@Slf4j
public class TimeCostAspect {
    private static final String POINT = "execution (* com.ywl.study.springsecurity..*.mapper..*.*(..)) || execution (* com.ywl.study.springsecurity.mapper..*.*(..))";

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut(POINT)
    public void performance() {
    }

    @Around("performance()")
    public Object watchPerformance(ProceedingJoinPoint joinPoint){
        Object obj=null;
        try{
            Object[] args = joinPoint.getArgs();
            long startTime = System.currentTimeMillis();
             obj = joinPoint.proceed(args);
            long endTime = System.currentTimeMillis();
            String method = joinPoint.getSignature().getName();
            String className = joinPoint.getSignature().getDeclaringTypeName();
            AspectVo aspectVo = AspectVo.getInstance(className, method, endTime - startTime);
            log.info(aspectVo.toString());
            redisUtil.lSet(AspectVo.MESSAGEQUEUE, JSON.toJSONString(aspectVo));
        }finally {
            return obj;
        }

    }
}
