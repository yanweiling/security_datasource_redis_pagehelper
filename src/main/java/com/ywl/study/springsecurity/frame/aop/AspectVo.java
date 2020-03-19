package com.ywl.study.springsecurity.frame.aop;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Slf4j
@Data
@Getter
@Setter
public class AspectVo implements Serializable {
    public static final String MESSAGEQUEUE="logs:exe:queue";

    private String className;
    private String method;
    private Long cost;
    private String message="";
    private Date ctime=new Date(System.currentTimeMillis());

    public static AspectVo getInstance(String className,String method,Long cost){
        AspectVo aspectVo = new AspectVo();
        aspectVo.className = className;
        aspectVo.method = method;
        aspectVo.cost = cost;
        return aspectVo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("[").append(className).append("] cost=").append(cost).append("ms.");
        return sb.toString();
    }
}
