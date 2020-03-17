package com.ywl.study.springsecurity.entity;

import java.io.Serializable;
import java.util.List;

public class Gw implements Serializable {
    private String id;
    private String gw_mc;
    private String jg_dm;
    private String yxbz;

    private DmSwjg dm_swjg;

    private List<Js> jsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGw_mc() {
        return gw_mc;
    }

    public void setGw_mc(String gw_mc) {
        this.gw_mc = gw_mc;
    }

    public String getJg_dm() {
        return jg_dm;
    }

    public void setJg_dm(String jg_dm) {
        this.jg_dm = jg_dm;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    public List<Js> getJsList() {
        return jsList;
    }

    public void setJsList(List<Js> jsList) {
        this.jsList = jsList;
    }

    public DmSwjg getDm_swjg() {
        return dm_swjg;
    }

    public void setDm_swjg(DmSwjg dm_swjg) {
        this.dm_swjg = dm_swjg;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Gw{");
        sb.append("id='").append(id).append('\'');
        sb.append(", gw_mc='").append(gw_mc).append('\'');
        sb.append(", yxbz='").append(yxbz).append('\'');
        sb.append(", jsList=").append(jsList);
        sb.append('}');
        return sb.toString();
    }
}
