package com.ywl.study.springsecurity.frame.aop;

import com.ywl.study.springsecurity.datasource.CurDataSource;
import com.ywl.study.springsecurity.datasource.DataSourceNames;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.aspectj.lang.annotation.Aspect;

@Mapper
public interface AspectDao {
    String tableName="XT_EXECUTE_LOG";

    @CurDataSource(name = DataSourceNames.FIRST)
    @Insert("INSERT INTO " + tableName + "(CLASSNAME, METHOD, COST, MESSAGE) VALUES (#{className},#{method},#{cost},#{message})")
    int insert(AspectVo aspectVo);

}
