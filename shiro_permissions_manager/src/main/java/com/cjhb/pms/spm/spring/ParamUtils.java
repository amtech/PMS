package com.cjhb.pms.spm.spring;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 基于Spring的HttpServletRequest参数辅助类
 * 
 * @author ArchX[archx@foxmail.com]
 */
public abstract class ParamUtils {

    /**
     * 将参数集合转换为实体类
     * 
     * @param params HttpServletRequest.getParameterMap()
     * @param clazz
     * @return
     */
    public static <T> T parse(Map<String, String[]> params, Class<T> clazz) {
        T target = BeanUtils.instantiate(clazz);
        Iterator<Entry<String, String[]>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String[]> e = it.next();
            Field field = ReflectionUtils.findField(clazz, e.getKey());
            if (field == null)
                continue;
            Class<?> targetType = field.getType();
            Object value = convert(e.getValue(), targetType);
            if (value == null)
                continue;
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, target, value);
        }
        return target;
    }

    /**
     * 将字符串转换成为原始数据类型
     * 
     * @param source
     * @param targetType
     * @return
     */
    public static <T> T convert(String[] source, Class<T> targetType) {
        // 空字符串或非原始数据类型不予转换
        boolean special = (source.length == 1 && !StringUtils.hasText(source[0]) || !targetType.isPrimitive());
        DefaultConversionService conversion = new DefaultConversionService();
        return special ? null : conversion.convert(source, targetType);
    }

    /**
     * 将字符串转换成为原始数据类型
     * 
     * @param source
     * @param targetType
     * @return
     */
    public static <T> T convert(String source, Class<T> targetType) {
        // 空字符串或非原始数据类型不予转换
        boolean special = (!StringUtils.hasText(source) || !targetType.isPrimitive());
        DefaultConversionService conversion = new DefaultConversionService();
        return special ? null : conversion.convert(source, targetType);
    }
}
