package com.cjhb.pms.service.spm.impl;

import java.util.List;

import org.springagg.web.bean.Role;
import org.springagg.web.dao.RoleMapper;
import org.springagg.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色业务实现
 * 
 * @author ArchX[archx@foxmail.com]
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int create(Role role) {
        return roleMapper.create(role);
    }

    @Override
    public int update(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public int delete(int rid) {
        return roleMapper.delete(rid);
    }

    @Override
    public Role get(int rid) {
        return roleMapper.get(rid);
    }

    @Override
    public List<Role> all() {
        return roleMapper.all();
    }

}
