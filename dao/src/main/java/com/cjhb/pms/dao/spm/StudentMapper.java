package com.cjhb.pms.dao.spm;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springagg.web.bean.Student;
import org.springframework.stereotype.Repository;

/**
 * 学生信息持久化接口
 * @author ArchX[archx@foxmail.com]
 */
@Repository
public interface StudentMapper {
    List<Student> all(RowBounds pagination);
}
