package com.cjhb.pms.spm.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springagg.mybatis.Pagination;
import org.springagg.web.bean.Student;
import org.springagg.web.service.StudentService;
import org.springagg.web.utils.W2uiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService ssv;

    @RequestMapping(method = RequestMethod.GET)
    public String view() {
        return "/student/list";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request) {
        Pagination pagination = getPagination(request);
        List<Student> list = ssv.all(pagination);
        return W2uiUtils.toW2uiGrid(list, pagination);
    }

    /**
     * 构建分页参数
     * 
     * @param request
     * @return
     */
    private Pagination getPagination(HttpServletRequest request) {
        String offset = request.getParameter("offset");
        String limit = request.getParameter("limit");
        int off = StringUtils.isEmpty(offset) ? 0 : Integer.parseInt(offset);
        int lim = StringUtils.isEmpty(limit) ? 30 : Integer.parseInt(limit);

        return new Pagination(off, lim);
    }
}
