//package com.ywl.study.springsecurity.service.impl;
//
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.ywl.study.springsecurity.datasource.DataSourceNames;
//import com.ywl.study.springsecurity.datasource.CurDataSource;
//import com.ywl.study.springsecurity.entity.SysUser;
//import com.ywl.study.springsecurity.mapper.SysUserMapper;
//import com.ywl.study.springsecurity.service.SysUserService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
//    @Override
//    public SysUser findUserByFirstDb(long id) {
//        return this.baseMapper.selectById(id);
//    }
//
//    @CurDataSource(name = DataSourceNames.SECOND)
//    @Override
//    public SysUser findUserBySecondDb(long id) {
//        return this.baseMapper.selectById(id);
//    }
//}
