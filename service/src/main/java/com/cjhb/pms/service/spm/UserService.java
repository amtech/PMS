package com.cjhb.pms.service.spm;

import com.cjhb.pms.domain.spm_pojo.User;

import java.util.List;
import java.util.Set;


/**
 * 用户业务接口
 * 
 * @author ArchX[archx@foxmail.com]
 */
public interface UserService {
    int create(User user);

    int update(User user);

    int delete(int uid);

    User get(int uid);

    User find(String username);

    List<User> all();

    Set<String> getRoles(String username);

    Set<String> getPermissions(String username);
}
