package com.ywl.study.springsecurity.entity;

import java.io.Serializable;

public class Gnmk implements Serializable {
    private String id;
    private String mk_mc;
    private String mk_url;
    private String yxbz;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMk_mc() {
        return mk_mc;
    }

    public void setMk_mc(String mk_mc) {
        this.mk_mc = mk_mc;
    }

    public String getMk_url() {
        return mk_url;
    }

    public void setMk_url(String mk_url) {
        this.mk_url = mk_url;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Gnmk{");
        sb.append("id='").append(id).append('\'');
        sb.append(", mk_mc='").append(mk_mc).append('\'');
        sb.append(", mk_url='").append(mk_url).append('\'');
        sb.append(", yxbz='").append(yxbz).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
