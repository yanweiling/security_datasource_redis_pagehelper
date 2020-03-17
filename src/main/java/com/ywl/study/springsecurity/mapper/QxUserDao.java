package com.ywl.study.springsecurity.mapper;

import com.ywl.study.springsecurity.mapper.base.BaseDao;
import com.ywl.study.springsecurity.entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface QxUserDao extends BaseDao {
    @Select("select * from qx_user where id = #{id}")
    QxUser findById(String id);

    @Insert("insert into qx_user(username,password) values(#{username},#{password})")
    int save(QxUser user);

    @Select("select * from qx_user where name = #{name}")
    @Results({@Result(column = "userid", property = "gwList", many = @Many(select = "findGwByUserId", fetchType = FetchType.EAGER)),
            @Result(column = "userid", property = "userid"),
            @Result(column = "czry_dm", property = "czry", one = @One(select = "findCzryByDm", fetchType = FetchType.EAGER)),
            @Result(column = "czry_dm", property = "czry_dm")})
    QxUser findByName(String name);

    @Select("select gw.* from qx_gw gw left join qx_user_gw ug on gw.gw_dm=ug.gw_dm where ug.userid=#{userid}")
    @Results({@Result(column = "gw_dm", property = "gnmbList", many = @Many(select = "findGnmbByGwDm", fetchType = FetchType.EAGER)),
            @Result(column = "gw_dm", property = "gw_dm"),
            @Result(column = "jg_dm", property = "swjg", one = @One(select = "findSwjg", fetchType = FetchType.EAGER)),
            @Result(column = "jg_dm", property = "jg_dm")})
    List<QxGw> findGwByUserId(String userid);

    @Select("select * from qx_gnmb mb left join qx_gw_gnmb gg on mb.gnmb_dm=gg.gnmb_dm where gg.gw_dm = #{gw_dm}")
    @Results({@Result(column = "gnmb_dm", property = "gnmkList", many = @Many(select = "findGnmkByGnmbDm", fetchType = FetchType.EAGER)),
            @Result(column = "gnmb_dm", property = "gnmb_dm")})
    List<QxGnmb> findGnmbByGwDm(String gw_dm);

    @Select("select a.czry_dm, a.czry_mc, a.swjg_dm, b.swjg_mc, a.qx_swjg_dm, a.swry_dm, a.ts_nsrsbh, a.yxbz, a.xybz " +
            "from dm_czry a left join dm_swjg b on a.swjg_dm = b.swjg_dm " +
            "where czry_dm = #{czry_dm} ")
    DmCzry findCzryByDm(String czry_dm);

    //@Select("select mk.* from qx_gnmk mk left join qx_gnmb_gnmk gg on mk.gnmk_dm=gg.gnmk_dm where gg.gnmb_dm=#{gnmb_dm}")
    @Select("select a.jd_dm, a.fjd_dm, a.jd_mc, a.jd_order, b.gnmk_dm, b.gnmk_hzmc, b.gnmk_ljmc, b.mklx_dm, b.ywhj_dm, b.systemname, b.cybj, b.cfdk, b.dkwz, b.showleft, b.showtop, b.showintree " +
            "from (select jd_dm, fjd_dm, jd_mc, jd_order, gnmk_dm from qx_gnmb_gnmk where gnmb_dm=#{gnmb_dm}) a " +
            "left join (select gnmk_dm, gnmk_hzmc, gnmk_ljmc, mklx_dm, ywhj_dm, systemname, cybj, cfdk, dkwz, showleft, showtop, showintree from qx_gnmk where yxbz='Y' and  xybz='Y' ) b on a.gnmk_dm = b.gnmk_dm " +
            "start with fjd_dm='JKPT' " +
            "connect by prior jd_dm=fjd_dm " +
            "order siblings by jd_order")
    List<QxGnmk> findGnmkByGnmbDm(String gnmb_dm);

    @Select("select * from dm_swjg where swjg_dm=#{swjg_dm}")
    DmSwjg findSwjg(String swjg_dm);

    @Select("select * from qx_gnmk")
    List<QxGnmk> findAllGnmk();

    @Select("select * from qx_gnmk_tree t connect by fjd_dm = prior jd_dm start with fjd_dm='0' order SIBLINGS  by jd_order asc")
    List<QxGnmkTree> findGnmkTree();
}
