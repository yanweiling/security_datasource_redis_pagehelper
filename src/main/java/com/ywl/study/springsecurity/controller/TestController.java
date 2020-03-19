package com.ywl.study.springsecurity.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywl.study.springsecurity.entity.QxGnmk;
import com.ywl.study.springsecurity.mapper.QxUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    QxUserDao qxUserDao;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        return "你好";
    }

    @GetMapping("/getQxGnmkes")
    public PageInfo<QxGnmk> selectByPage(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum){
        //pageNum:表示第几页  pageSize:表示一页展示的数据
        PageHelper.startPage(pageNum,3);
        List<QxGnmk> list= qxUserDao.findAllGnmk();
        PageInfo<QxGnmk> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

}
