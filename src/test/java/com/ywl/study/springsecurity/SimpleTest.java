package com.ywl.study.springsecurity;

import com.alibaba.fastjson.JSONObject;
import com.ywl.study.springsecurity.entity.FrameUserDetails;
import com.ywl.study.springsecurity.frame.aop.AspectVo;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Executors;

@SpringBootTest
public class SimpleTest {
    @Test
    public void test1(){
        String json="{\"className\":\"com.ywl.study.springsecurity.mapper.QxUserDao\",\"cost\":911,\"ctime\":1584601081427,\"method\":\"findGnmkTree\"}";
        System.out.println(json.toString());
        AspectVo aspectVo=JSONObject.parseObject(json, AspectVo.class);
    }
}
