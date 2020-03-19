package com.ywl.study.springsecurity.frame.aop;

import com.alibaba.fastjson.JSON;
import com.ywl.study.springsecurity.entity.FrameUserDetails;
import com.ywl.study.springsecurity.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoginLogAspect {
    private static final String POINT="execution (* com.ywl.study.springsecurity.security.service.UserInfoService.loadUserByUsername(..))";

    @Autowired
    RedisUtil redisUtil;
    @Pointcut(POINT)
   public void performance(){}

   //要将point执行的异常抛出去

    /**
     * 做切面记录日志，不应该影响主干流程
     * @param point
     * @return
     * @throws Throwable
     */
   @Around("performance()")
   public Object doSomething(ProceedingJoinPoint point) {
       Object result=null;
        try{
            long beginTime=System.currentTimeMillis();
            Object[] args=point.getArgs();
            result=point.proceed(args);
            Signature signature=point.getSignature();
            String method=signature.getName();
            String className=signature.getDeclaringTypeName();
            long endTime=System.currentTimeMillis();
            FrameUserDetails frameUserDetails= (FrameUserDetails) result;
            AspectVo aspectVo=AspectVo.getInstance(className,method,endTime-beginTime);
            aspectVo.setMessage(frameUserDetails.getUserid()+" [ "+frameUserDetails.getUsername()+" ]");
            log.info(aspectVo.toString());
//            if(1==1){int a=1/0;}  如果发生异常，用户userDetail就无法返回了，所以要用finally，保证正常返回用户信息，不影响主干流程
            redisUtil.lSet(AspectVo.MESSAGEQUEUE, JSON.toJSONString(aspectVo));
        }finally {
            return result;
        }
   }

}
