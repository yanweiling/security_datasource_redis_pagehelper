package com.ywl.study.springsecurity.entity;


import java.io.Serializable;
import java.util.List;

public class QxGnmb implements Serializable {
    private String gnmb_dm;
    private String gnmb_mc;
    private String jg_dm;
    private String jssx_dm;
    private String sfgxjs;
    private String ss_gw_dm;
    private String gzlbz;
    private String xybz;
    private String yxbz;
    private String zn_dm;
    private String sjlyxt;

    private List<QxGnmk> gnmkList;

    public String getGnmb_dm() {
        return gnmb_dm;
    }

    public void setGnmb_dm(String gnmb_dm) {
        this.gnmb_dm = gnmb_dm;
    }

    public String getGnmb_mc() {
        return gnmb_mc;
    }

    public void setGnmb_mc(String gnmb_mc) {
        this.gnmb_mc = gnmb_mc;
    }

    public String getJg_dm() {
        return jg_dm;
    }

    public void setJg_dm(String jg_dm) {
        this.jg_dm = jg_dm;
    }

    public String getJssx_dm() {
        return jssx_dm;
    }

    public void setJssx_dm(String jssx_dm) {
        this.jssx_dm = jssx_dm;
    }

    public String getSfgxjs() {
        return sfgxjs;
    }

    public void setSfgxjs(String sfgxjs) {
        this.sfgxjs = sfgxjs;
    }

    public String getSs_gw_dm() {
        return ss_gw_dm;
    }

    public void setSs_gw_dm(String ss_gw_dm) {
        this.ss_gw_dm = ss_gw_dm;
    }

    public String getGzlbz() {
        return gzlbz;
    }

    public void setGzlbz(String gzlbz) {
        this.gzlbz = gzlbz;
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

    public List<QxGnmk> getGnmkList() {
        return gnmkList;
    }

    public void setGnmkList(List<QxGnmk> gnmkList) {
        this.gnmkList = gnmkList;
    }
}
