package com.cjhb.pms.domain.spm_pojo;

/**
 * 用户实体
 * 
 * @author ArchX[archx@foxmail.com]
 */
public class User extends W2uiModel {
    private int uid;
    private String username;
    private String password;
    private String roles; // 角色id1,id2...

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

}
