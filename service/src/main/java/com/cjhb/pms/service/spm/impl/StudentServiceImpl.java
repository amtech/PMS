package com.cjhb.pms.service.spm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springagg.web.bean.Student;
import org.springagg.web.dao.StudentMapper;
import org.springagg.web.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper mapper;

    @Override
    public List<Student> all(RowBounds pagination) {
        return mapper.all(pagination);
    }

}
