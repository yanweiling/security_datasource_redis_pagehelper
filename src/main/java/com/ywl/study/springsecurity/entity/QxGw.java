package com.ywl.study.springsecurity.entity;


import java.io.Serializable;
import java.util.List;

public class QxGw implements Serializable {
    private String gw_dm;
    private String gw_mc;
    private String jg_dm;
    private String qx_jg_dm;
    private String ywhj_dm;
    private String gwlx;
    private String ywbs;
    private String xybz;
    private String yxbz;
    private String sj_gw_dm;
    private String tbtj_bz;
    private String zn_dm;
    private String sjlyxt;

    private DmSwjg swjg;
    private List<QxGnmb> gnmbList;

    public String getGw_dm() {
        return gw_dm;
    }

    public void setGw_dm(String gw_dm) {
        this.gw_dm = gw_dm;
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

    public String getQx_jg_dm() {
        return qx_jg_dm;
    }

    public void setQx_jg_dm(String qx_jg_dm) {
        this.qx_jg_dm = qx_jg_dm;
    }

    public String getYwhj_dm() {
        return ywhj_dm;
    }

    public void setYwhj_dm(String ywhj_dm) {
        this.ywhj_dm = ywhj_dm;
    }

    public String getGwlx() {
        return gwlx;
    }

    public void setGwlx(String gwlx) {
        this.gwlx = gwlx;
    }

    public String getYwbs() {
        return ywbs;
    }

    public void setYwbs(String ywbs) {
        this.ywbs = ywbs;
    }

    public String getXybz() {
        return xybz;
    }

    public void setXybz(String xybz) {
        this.xybz = xybz;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    public String getSj_gw_dm() {
        return sj_gw_dm;
    }

    public void setSj_gw_dm(String sj_gw_dm) {
        this.sj_gw_dm = sj_gw_dm;
    }

    public String getTbtj_bz() {
        return tbtj_bz;
    }

    public void setTbtj_bz(String tbtj_bz) {
        this.tbtj_bz = tbtj_bz;
    }

    public String getZn_dm() {
        return zn_dm;
    }

    public void setZn_dm(String zn_dm) {
        this.zn_dm = zn_dm;
    }

    public String getSjlyxt() {
        return sjlyxt;
    }

    public void setSjlyxt(String sjlyxt) {
        this.sjlyxt = sjlyxt;
    }

    public DmSwjg getSwjg() {
        return swjg;
    }

    public void setSwjg(DmSwjg swjg) {
        this.swjg = swjg;
    }

    public List<QxGnmb> getGnmbList() {
        return gnmbList;
    }

    public void setGnmbList(List<QxGnmb> gnmbList) {
        this.gnmbList = gnmbList;
    }
}
