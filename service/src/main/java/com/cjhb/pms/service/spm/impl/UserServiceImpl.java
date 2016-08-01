package com.cjhb.pms.service.spm.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springagg.web.bean.Resource;
import org.springagg.web.bean.Role;
import org.springagg.web.bean.User;
import org.springagg.web.dao.ResourceMapper;
import org.springagg.web.dao.RoleMapper;
import org.springagg.web.dao.UserMapper;
import org.springagg.web.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户业务实现
 * 
 * @author ArchX[archx@foxmail.com]
 */
@Service
public class UserServiceImpl implements UserService {

    @javax.annotation.Resource
    private UserMapper userMapper;

    @javax.annotation.Resource
    private RoleMapper roleMapper;

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    @Override
    public int create(User user) {
        return userMapper.create(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int delete(int uid) {
        return userMapper.delete(uid);
    }

    @Override
    public User get(int uid) {
        return userMapper.get(uid);
    }

    @Override
    public User find(String username) {
        return userMapper.find(username);
    }

    @Override
    public List<User> all() {
        return userMapper.all();
    }

    @Override
    public Set<String> getRoles(String username) {
        User user = find(username);
        return getRoles(user);
    }

    @Override
    public Set<String> getPermissions(String username) {
        User user = find(username);
        return getPermissions(user);
    }

    /**
     * 获取用户角色
     * 
     * @param user
     * @return
     */
    private Set<String> getRoles(User user) {
        if (user != null && user.getRoles() != null) {
            List<Role> roles = roleMapper.all();
            if (roles != null && roles.size() > 0) {
                Set<String> roleSet = new HashSet<String>();
                // 遍历计较
                for (Role role : roles) {
                    if (hasRole(role, user))
                        roleSet.add(role.getRole());
                }
                return roleSet;
            }
        }
        return Collections.emptySet();
    }

    /**
     * 获取用户权限
     * 
     * @param user
     * @return
     */
    private Set<String> getPermissions(User user) {
        if (user != null && user.getRoles() != null) {
            List<Role> roles = roleMapper.all();
            if (roles != null && roles.size() > 0) {
                Set<Role> roleSet = new HashSet<Role>();
                for (Role role : roles) {
                    if (hasRole(role, user))
                        roleSet.add(role);
                }
                return getPermissions(roleSet);
            }
        }
        return Collections.emptySet();
    }

    /**
     * 获取用户权限
     * 
     * @param roles
     * @return
     */
    private Set<String> getPermissions(Set<Role> roles) {
        if (!roles.isEmpty()) {
            List<Resource> resources = resourceMapper.all();
            if (resources != null && resources.size() > 0) {
                Set<String> permSet = new HashSet<String>();
                for (Resource res : resources) {
                    if (res.getPermissions() != null && hasPermission(res, roles))
                        permSet.add(res.getPermissions());
                }
                return permSet;
            }
        }

        return Collections.emptySet();
    }

    /**
     * 判断用户是有拥有角色
     * 
     * @param role
     * @param user
     * @return
     */
    private boolean hasRole(Role role, User user) {
        String[] rids = user.getRoles().split(",");
        for (String rid : rids) {
            if (StringUtils.hasText(rid) && Integer.parseInt(rid) == role.getRid())
                return true;
        }
        return false;
    }

    /**
     * 判断角色是否拥有资源
     * 
     * @param res
     * @param roles
     * @return
     */
    private boolean hasPermission(Resource res, Set<Role> roles) {
        for (Role role : roles) {
            if (role.getResources() == null)
                continue;
            String[] reids = role.getResources().split(",");
            for (String reid : reids) {
                if (StringUtils.hasText(reid) && Integer.parseInt(reid) == res.getReid())
                    return true;
            }
        }
        return false;
    }
}
