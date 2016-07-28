package com.cjhb.ssm.credit.system.entity.system;

import java.util.List;

import com.credit.entity.BaseEntity;

public class AuRole extends BaseEntity {

	private static final long serialVersionUID = 733089904618220207L;

	private String roleName;
    
    private String roleCode;

    private String isValid;

    private String isDel;
    
    private List<AuMenu> menus;
    
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
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

    public List<AuMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<AuMenu> menus) {
		this.menus = menus;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getTreeType() {
		return "0";
	}

	public String getTreeLevel() {
		return "1";
	}
}