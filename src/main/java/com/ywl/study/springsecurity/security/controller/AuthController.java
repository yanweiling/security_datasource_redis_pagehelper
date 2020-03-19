package com.ywl.study.springsecurity.security.controller;

import com.ywl.study.springsecurity.entity.QxGnmkTree;
import com.ywl.study.springsecurity.redis.RedisUtil;
import com.ywl.study.springsecurity.security.TokenConst;
import com.ywl.study.springsecurity.security.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    RedisUtil redisUtil;

    public void initTree() {
        List<QxGnmkTree> gnmkTreeList = userInfoService.findGnmkTree();
        for (QxGnmkTree gnmkTree : gnmkTreeList) {
            redisUtil.set(TokenConst.TOKEN_GNMK + gnmkTree.getJd_dm(), gnmkTree);
        }
    }
}
