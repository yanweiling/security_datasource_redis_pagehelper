package com.ywl.study.springsecurity.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String id;
    private String username;
    private String password;
    private String role;
    private String yxbz;
    private Long pwrqq;
    private Long pwrqz;

    private List<Gw> gwList;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    public Long getPwrqq() {
        return pwrqq;
    }

    public void setPwrqq(Long pwrqq) {
        this.pwrqq = pwrqq;
    }

    public Long getPwrqz() {
        return pwrqz;
    }

    public void setPwrqz(Long pwrqz) {
        this.pwrqz = pwrqz;
    }

    public List<Gw> getGwList() {
        return gwList;
    }

    public void setGwList(List<Gw> gwList) {
        this.gwList = gwList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id='").append(id).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", yxbz='").append(yxbz).append('\'');
        sb.append(", pwrqq=").append(pwrqq);
        sb.append(", pwrqz=").append(pwrqz);
        sb.append(", gwList=").append(gwList);
        sb.append('}');
        return sb.toString();
    }
}
