package com.ywl.study.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FrameUserDetails implements UserDetails {
    private final String username;
    private String password;
    private String userid;
    private QxUser qxUser;
    private final boolean enabled;
    private List<Role> roleList;

    public FrameUserDetails(String username, String password, List<Role> roleList) {
        this(username, password, null, null, true, roleList);
    }

    public FrameUserDetails(String username, String password, QxUser qxUser, List<Role> roleList) {
        this(username, password, null, qxUser, true, roleList);
    }

    /**
     * 功能描述:authUser
     *
     * @param username username
     * @param password password
     * @param enabled  enabled
     * @param roleList roleList
     */
    public FrameUserDetails(String username, String password, String userid, QxUser qxUser, boolean enabled, List<Role> roleList) {
        if (username == null || "".equals(username) || password == null) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        } else {
            this.username = username;
            this.password = password;
            this.userid = userid;
            this.qxUser = qxUser;
            this.enabled = enabled;
            this.roleList = roleList;
            return;
        }
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * @return the authorities
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /**
     * @return the accountNonExpired
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return the accountNonLocked
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return the credentialsNonExpired
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return the enabled
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public QxUser getQxUser() {
        return qxUser;
    }

    public void setQxUser(QxUser qxUser) {
        this.qxUser = qxUser;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof FrameUserDetails)
            return username.equals(((FrameUserDetails) rhs).username);
        else
            return false;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
