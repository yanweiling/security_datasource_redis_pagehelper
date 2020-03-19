package com.ywl.study.springsecurity.frame.init;

import com.ywl.study.springsecurity.frame.aop.AspectService;
import com.ywl.study.springsecurity.security.controller.AuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
public class ScheduleApplicationRunner implements ApplicationRunner {
    @Autowired
    AuthController authController;
    @Autowired
    AspectService aspectService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        authController.initTree();
        aspectService.exeLogToDB();
    }
}
