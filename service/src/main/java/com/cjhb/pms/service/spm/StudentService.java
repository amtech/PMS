package com.cjhb.pms.service.spm;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springagg.web.bean.Student;

/**
 * 学生信息业务接口
 * 
 * @author ArchX[archx@foxmail.com]
 */
public interface StudentService {
    List<Student> all(RowBounds pagination);
}
