package com.ywl.study.springsecurity.frame.aop;

import com.alibaba.fastjson.JSONObject;
import com.ywl.study.springsecurity.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AspectService {

    @Autowired
    AspectDao aspectDao;
    @Autowired
    RedisUtil redisUtil;
    public void exeLogToDB(){
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
             while(true){
                 AspectVo aspectVo= JSONObject.parseObject(redisUtil.rPop(AspectVo.MESSAGEQUEUE),AspectVo.class);
                 if(aspectVo!=null){
                     aspectDao.insert(aspectVo);
                 }
             }
            }
        },0,10, TimeUnit.SECONDS);
    }
}
