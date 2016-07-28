package com.cjhb.ssm.credit.system.entity.system;

import java.util.List;

import com.credit.entity.BaseEntity;

/**
 * @author: kevin
 * @pageName: com.credit.entity.system
 * @fileName: AuMenu.java
 * @date: 2014-3-26
 * @doc: AuMenu实体类
 */
public class AuMenu extends BaseEntity {

	private static final long serialVersionUID = 5987668401617260164L;

	private String menuName;

    private String menuCode;
    
    private String menuLevel;
    
    private String hasChild;

    private String iconCode;

    private String menuPath;

    private String superId;

    private Integer menuOrder;

    private String isValid;

    private String isDel;
    
    private List<AuRole> roles;
    
    public AuMenu(){
    	
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    public String getIconCode() {
        return iconCode;
    }

    public void setIconCode(String iconCode) {
        this.iconCode = iconCode == null ? null : iconCode.trim();
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath == null ? null : menuPath.trim();
    }

    public String getSuperId() {
        return superId;
    }

    public void setSuperId(String superId) {
        this.superId = superId == null ? null : superId.trim();
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getHasChild() {
		return hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	public List<AuRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AuRole> roles) {
		this.roles = roles;
	}

}