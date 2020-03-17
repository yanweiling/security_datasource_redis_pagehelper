package com.ywl.study.springsecurity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class SpringsecurityApplicationTests {
//    @Autowired
//    private SysUserService userService;
    @Test
    public void contextLoads() {
//        SysUser user = userService.getById(1);

    }
//
//    @Test
//    public void test() {
//        SysUser user = userService.findUserByFirstDb(1);
//        log.info("第一个数据库 : [{}]", user.toString());
//        SysUser user2 = userService.findUserBySecondDb(1);
//        log.info("第二个数据库 : [{}]", user2.toString());
//    }
}
