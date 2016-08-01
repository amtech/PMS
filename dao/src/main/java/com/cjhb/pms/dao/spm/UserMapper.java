package com.cjhb.pms.dao.spm;

import java.util.List;

import org.springagg.web.bean.User;
import org.springframework.stereotype.Repository;

/**
 * 用户持久化接口
 * 
 * @author ArchX[archx@foxmail.com]
 */
@Repository
public interface UserMapper {

    int create(User user);

    int update(User user);

    int delete(int uid);

    User get(int uid);

    User find(String username);

    List<User> all();

}
