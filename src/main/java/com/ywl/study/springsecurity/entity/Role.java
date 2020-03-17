package com.ywl.study.springsecurity.entity;

import java.io.Serializable;

public class Role implements Serializable {
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
