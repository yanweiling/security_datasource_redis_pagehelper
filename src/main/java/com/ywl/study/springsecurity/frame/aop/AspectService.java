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
        log.info("日志表记录....");
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
              log.info("任务。。。。");
             while(true){
                 log.info("获取"+AspectVo.MESSAGEQUEUE+"中信息，导入日志表中");
                 AspectVo aspectVo= JSONObject.parseObject(redisUtil.rPop(AspectVo.MESSAGEQUEUE),AspectVo.class);
                 log.info(aspectVo==null?"没有数据": "获得"+aspectVo.toString());
                 if(aspectVo!=null){
                     aspectDao.insert(aspectVo);
                     log.info("插入成功");
                 }
             }

            }
        },0,10, TimeUnit.SECONDS);
    }
}
