package com.ywl.study.springsecurity.entity;

import java.io.Serializable;
import java.util.List;

public class Js implements Serializable {
    private String id;
    private String js_mc;
    private String yxbz;

    private List<Gnmk> gnmkList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJs_mc() {
        return js_mc;
    }

    public void setJs_mc(String js_mc) {
        this.js_mc = js_mc;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    public List<Gnmk> getGnmkList() {
        return gnmkList;
    }

    public void setGnmkList(List<Gnmk> gnmkList) {
        this.gnmkList = gnmkList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Js{");
        sb.append("id='").append(id).append('\'');
        sb.append(", js_mc='").append(js_mc).append('\'');
        sb.append(", yxbz='").append(yxbz).append('\'');
        sb.append(", gnmkList=").append(gnmkList);
        sb.append('}');
        return sb.toString();
    }
}
