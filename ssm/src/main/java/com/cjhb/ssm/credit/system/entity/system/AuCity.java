package com.cjhb.ssm.credit.system.entity.system;

import com.cjhb.ssm.credit.system.entity.BaseEntity;

public class AuCity extends BaseEntity {

	private static final long serialVersionUID = -9130811130808411814L;

	private String cityCode;

    private String cityName;
    private String cityPinyin;

    private String cityType;

    private String superCityId;
    
    private String hasChild;

	private String isValid;

    private String isDel;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getCityType() {
        return cityType;
    }

    public void setCityType(String cityType) {
        this.cityType = cityType == null ? null : cityType.trim();
    }

    public String getSuperCityId() {
        return superCityId;
    }

    public void setSuperCityId(String superCityId) {
        this.superCityId = superCityId == null ? null : superCityId.trim();
    }

    public String getHasChild() {
		return hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
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

	public String getCityPinyin() {
		return cityPinyin;
	}

	public void setCityPinyin(String cityPinyin) {
		this.cityPinyin = cityPinyin;
	}

}