package com.ywl.study.springsecurity.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QxUser implements Serializable {
    private String userid;
    private String name;
    private String czry_dm;
    @JsonIgnore
    private String password;
    private String kllx;
    private Date pwrqq;
    private Date pwrqz;
    private String grantrole;
    private String xybz;
    private String yxbz;
    private Integer priority;
    private String mh_czrydm;
    private String sjlyxt;

    private DmCzry czry;
    private List<QxGw> gwList;
    private String role = "ROLE_USER";

    public List<QxGnmk> getGwGnmk(String gw_dm) {
        List<QxGnmk> gnmkList = new ArrayList<>();
        for (QxGw gw : gwList) {
            if (gw_dm.equals(gw.getGw_dm())) {
                List<QxGnmb> gnmbList = gw.getGnmbList();
                for (QxGnmb gnmb : gnmbList) {
                    gnmkList.addAll(gnmb.getGnmkList());
                }
            }
        }
        return gnmkList;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCzry_dm() {
        return czry_dm;
    }

    public void setCzry_dm(String czry_dm) {
        this.czry_dm = czry_dm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKllx() {
        return kllx;
    }

    public void setKllx(String kllx) {
        this.kllx = kllx;
    }

    public Date getPwrqq() {
        return pwrqq;
    }

    public void setPwrqq(Date pwrqq) {
        this.pwrqq = pwrqq;
    }

    public Date getPwrqz() {
        return pwrqz;
    }

    public void setPwrqz(Date pwrqz) {
        this.pwrqz = pwrqz;
    }

    public String getGrantrole() {
        return grantrole;
    }

    public void setGrantrole(String grantrole) {
        this.grantrole = grantrole;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getMh_czrydm() {
        return mh_czrydm;
    }

    public void setMh_czrydm(String mh_czrydm) {
        this.mh_czrydm = mh_czrydm;
    }

    public String getSjlyxt() {
        return sjlyxt;
    }

    public void setSjlyxt(String sjlyxt) {
        this.sjlyxt = sjlyxt;
    }

    public DmCzry getCzry() {
        return czry;
    }

    public void setCzry(DmCzry czry) {
        this.czry = czry;
    }

    public List<QxGw> getGwList() {
        return gwList;
    }

    public void setGwList(List<QxGw> gwList) {
        this.gwList = gwList;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
