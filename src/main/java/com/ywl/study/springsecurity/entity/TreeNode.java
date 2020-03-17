package com.ywl.study.springsecurity.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeNode<T> implements Serializable {
    private String jd_dm;
    private String jd_mc;
    private String fjd_dm;
    private String fjd_mc;
    private List<T> children = new ArrayList<T>();

    public String getJd_dm() {
        return jd_dm;
    }

    public void setJd_dm(String jd_dm) {
        this.jd_dm = jd_dm;
    }

    public String getJd_mc() {
        return jd_mc;
    }

    public void setJd_mc(String jd_mc) {
        this.jd_mc = jd_mc;
    }

    public String getFjd_dm() {
        return fjd_dm;
    }

    public void setFjd_dm(String fjd_dm) {
        this.fjd_dm = fjd_dm;
    }

    public String getFjd_mc() {
        return fjd_mc;
    }

    public void setFjd_mc(String fjd_mc) {
        this.fjd_mc = fjd_mc;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode<?> that = (TreeNode<?>) o;
        return Objects.equals(jd_dm, that.jd_dm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jd_dm);
    }
}
