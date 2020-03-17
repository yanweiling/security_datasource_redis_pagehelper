package com.ywl.study.springsecurity;

import com.ywl.study.springsecurity.redis.RedisUtil;
import com.ywl.study.springsecurity.util.GUIDGen;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class FirstTest {
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void test1(){
        redisTemplate.opsForValue().set("redis_key", "redis_value");
    }
}
