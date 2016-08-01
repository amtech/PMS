package com.cjhb.pms.domain.spm_pojo;

/**
 * JSON对象模型
 * 
 * @author ArchX[archx@foxmail.com]
 */
public class JsonModel {
    private boolean success;
    private String status;
    private String message;
    private Object obj;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
