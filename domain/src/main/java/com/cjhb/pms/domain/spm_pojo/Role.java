package com.cjhb.pms.domain.spm_pojo;

/**
 * 角色实体
 * 
 * @author ArchX[archx@foxmail.com]
 */
public class Role extends W2uiModel {
    private int rid;
    private String role;
    private String description;
    private String resources; // 资源id1,id2...

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

}
