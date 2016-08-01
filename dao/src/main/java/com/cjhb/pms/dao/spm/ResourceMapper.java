package com.cjhb.pms.dao.spm;

import java.util.List;

import org.springagg.web.bean.Resource;
import org.springframework.stereotype.Repository;

/**
 * 资源持久化接口
 * 
 * @author ArchX[archx@foxmail.com]
 */
@Repository
public interface ResourceMapper {
    int create(Resource res);

    int update(Resource res);

    int delete(int reid);

    Resource get(int reid);

    List<Resource> all();
}
