package com.ywl.study.springsecurity;

import com.alibaba.fastjson.JSONObject;
import com.ywl.study.springsecurity.entity.DmCzry;
import com.ywl.study.springsecurity.frame.aop.AspectDao;
import com.ywl.study.springsecurity.frame.aop.AspectVo;
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

    @Autowired
    AspectDao aspectDao;

    /**
     * [
     *   "com.ywl.study.springsecurity.entity.DmCzry",
     *   {
     *     "czry_dm": "111",
     *     "czry_mc": null,
     *     "swjg_dm": null,
     *     "swjg_mc": "名称",
     *     "swry_dm": null,
     *     "qx_swjg_dm": null,
     *     "ts_nsrsbh": null,
     *     "xybz": "中国测试",
     *     "yxbz": null
     *   }
     * ]
     */
    @Test
    public void test1(){
        DmCzry d=new DmCzry();
        d.setCzry_dm("111");
        d.setSwjg_mc("名称");
        d.setXybz("中国测试");
        redisTemplate.opsForValue().set("redis_key1","测试");
    }
    @Test
    public void test2(){
        String json="{\"className\":\"com.ywl.study.springsecurity.mapper.QxUserDao\",\"cost\":911,\"ctime\":1584601081427,\"method\":\"findGnmkTree\"}";
        System.out.println(json.toString());
        AspectVo aspectVo= JSONObject.parseObject(json, AspectVo.class);
//        AspectVo aspectVo=new AspectVo();
//        aspectVo.setMessage("testMethod");
//        aspectVo.setClassName("testClass");
//        aspectVo.setMethod("fdsafsa");
//        aspectVo.setCost(1l);
        aspectDao.insert(aspectVo);
    }
}
