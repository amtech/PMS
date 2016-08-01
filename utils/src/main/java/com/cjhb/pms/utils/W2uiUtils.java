package com.cjhb.pms.utils;


import com.cjhb.pms.spm.mybatis.Pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class W2uiUtils {

    public static Map<String, Object> toW2uiGrid(List<?> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (list != null && list.size() > 0) {
            map.put("records", list);
            map.put("total", list.size());
        } else {
            map.put("total", 0);
        }
        return map;
    }

    public static Map<String, Object> toW2uiGrid(List<?> list, Pagination pagination) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (list != null && list.size() > 0) {
            map.put("records", list);
        }
        map.put("total", pagination.getTotal());
        return map;
    }

    public static Map<String, Object> toW2uiGrid(List<?> list, int total) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (list != null && list.size() > 0) {
            map.put("records", list);
        }
        map.put("total", total);
        return map;
    }
}
