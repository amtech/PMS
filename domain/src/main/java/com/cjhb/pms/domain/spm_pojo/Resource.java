package com.cjhb.pms.domain.spm_pojo;

import org.springagg.web.constants.ResourceType;

/**
 * 资源实体
 * 
 * @author ArchX[archx@foxmail.com]
 */
public class Resource extends W2uiModel {
    private int reid; // 编号
    private String name; // 名称
    private int type = ResourceType.MENU; // 菜单:0,按钮：1
    private String permissions; // 权限字符串
    private String url;
    private int pid; // 父级编号
    private String sorted; // 排序用 A01,A0101,A0102

    public int getReid() {
        return reid;
    }

    public void setReid(int reid) {
        this.reid = reid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getSorted() {
        return sorted;
    }

    public void setSorted(String sorted) {
        this.sorted = sorted;
    }

}
