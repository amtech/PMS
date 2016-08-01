package com.cjhb.pms.dao.spm;

/**
 * 数据访问层标识接口
 * <p>
 * 用于Spring整合MyBatis时,标识需要扫描的MAPPER<br>
 * org.mybatis.spring.mapper.MapperScannerConfigurer.markerInterface<br>
 * 
 * 配置后将会扫描指定包下所有继承了本接口的MAPPER接口
 * 
 * @author ArchX[archx@foxmail.com]
 */
public interface SqlMapper {
}
