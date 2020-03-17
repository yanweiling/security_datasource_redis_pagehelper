package com.ywl.study.springsecurity.mapper;

import com.ywl.study.springsecurity.mapper.base.BaseDao;
import com.ywl.study.springsecurity.entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


@Mapper
public interface UserInfoDao extends BaseDao {
    @Select("select * from x_user where id = #{id}")
    User findById(String id);

    @Insert("insert into x_user(username,password) values(#{username},#{password})")
    int save(User user);

    @Select("select * from x_user where username = #{username}")
    @Results({@Result(column = "id", property = "gwList", many = @Many(select = "com.dcits.frame.security.dao.UserInfoDao.findGwByUserId", fetchType = FetchType.EAGER))})
    User findByName(String username);

    @Select("select gw.* from x_gw gw left join x_user_gw ug on gw.id=ug.gw_id where ug.user_id=#{uid}")
    @Results({@Result(column = "id", property = "jsList", many = @Many(select = "com.dcits.frame.security.dao.UserInfoDao.findJsByGwId", fetchType = FetchType.EAGER)),
            @Result(column = "jg_dm", property = "jg_dm"),
            @Result(column = "jg_dm", property = "dm_swjg", one = @One(select = "com.dcits.frame.security.dao.UserInfoDao.findSwjg", fetchType = FetchType.EAGER))})
    List<Gw> findGwByUserId(String uid);

    @Select("select js.* from x_js js left join x_gw_js gj on js.id=gj.js_id where gj.gw_id=#{gw_id}")
    @Results({@Result(column = "id", property = "gnmkList", many = @Many(select = "com.dcits.frame.security.dao.UserInfoDao.findGnmkByJsId", fetchType = FetchType.EAGER))})
    List<Js> findJsByGwId(String gw_id);

    @Select("select mk.* from x_gnmk mk left join x_js_gnmk jg on mk.id=jg.mk_id where jg.js_id=#{js_id}")
    List<Gnmk> findGnmkByJsId(String js_id);

    @Select("select * from dm_swjg where swjg_dm=#{swjg_dm}")
    DmSwjg findSwjg(String swjg_dm);

    @Select("select * from x_gnmk")
    List<Gnmk> findAllGnmk();
}
