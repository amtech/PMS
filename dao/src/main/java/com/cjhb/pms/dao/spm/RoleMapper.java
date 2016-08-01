package com.cjhb.pms.dao.spm;

import java.util.List;

import org.springagg.web.bean.Role;
import org.springframework.stereotype.Repository;

/**
 * 角色持久化接口
 * 
 * @author ArchX[archx@foxmail.com]
 */
@Repository
public interface RoleMapper {
    int create(Role role);

    int update(Role role);

    int delete(int rid);

    Role get(int rid);

    List<Role> all();
}
