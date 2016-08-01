package com.cjhb.pms.service.spm;

import java.util.List;
import java.util.Set;

import org.springagg.web.bean.Resource;

/**
 * 资源业务接口
 * 
 * @author ArchX[archx@foxmail.com]
 */
public interface ResourceService {
    int create(Resource res);

    int update(Resource res);

    int delete(int reid);

    Resource get(int reid);

    List<Resource> all();

    List<Resource> getMenus(Set<String> permissions);
}
