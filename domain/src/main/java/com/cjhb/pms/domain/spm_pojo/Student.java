package com.cjhb.pms.domain.spm_pojo;

/**
 * 学生实体 演示分页插件使用
 * 
 * @author ArchX[archx@foxmail.com]
 */
public class Student extends W2uiModel {
    private String sn;
    private String name;
    private String sex;
    private String academic;
    private String classes;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

}
