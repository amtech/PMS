package com.cjhb.pms.service.spm;

import java.util.List;

import org.springagg.web.bean.Role;

/**
 * 角色业务接口
 * 
 * @author ArchX[archx@foxmail.com]
 */
public interface RoleService {
    int create(Role role);

    int update(Role role);

    int delete(int rid);

    Role get(int rid);

    List<Role> all();
}
