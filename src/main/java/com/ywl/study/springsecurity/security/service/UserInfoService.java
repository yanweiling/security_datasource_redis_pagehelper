package com.ywl.study.springsecurity.security.service;

import com.ywl.study.springsecurity.mapper.QxUserDao;
import com.ywl.study.springsecurity.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserInfoService implements UserDetailsService {
    public final static String ROLE_ADMIN = "ROLE_ADMIN_";
    @Autowired
    private QxUserDao qxUserDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        QxUser user = qxUserDao.findByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<Role> roleList = obtionGrantedAuthorities(user);
        FrameUserDetails userDetails = new FrameUserDetails(user.getName(), user.getPassword(), roleList);
        userDetails.setUserid(user.getUserid());
        userDetails.setQxUser(user);
        return userDetails;
    }

    public int save(QxUser user) {
        return qxUserDao.save(user);
    }

    public List<QxGnmkTree> findGnmkTree() {
        List<QxGnmkTree> gnmkTreeList = qxUserDao.findGnmkTree();
        return gnmkTreeList;
    }

    /**
     * 获得User的权限
     *
     * @param user 登录用户
     * @return Set 权限列表
     */
    private List<Role> obtionGrantedAuthorities(QxUser user) {
        List<Role> authSet = new ArrayList<>();
        if ("admin".equals(user.getName().trim().toLowerCase()) || "administrator".equals(user.getName().trim().toLowerCase())) {
            authSet.add(new Role(ROLE_ADMIN));
        } else {
            for (QxGw gw : user.getGwList()) {
                if (gw != null && gw.getGnmbList() != null && gw.getGnmbList().size() > 0) {
                    for (QxGnmb js : gw.getGnmbList()) {
                        if (js != null && js.getGnmkList() != null && js.getGnmkList().size() > 0) {
                            for (QxGnmk mk : js.getGnmkList()) {
                                authSet.add(new Role("ROLE_" + mk.getGnmk_dm()));
                            }
                        }
                    }
                }
            }
        }
        return authSet;
    }
}
