package com.zzz.fun.mapper.po;


import com.zzz.fun.mapper.BasePo;

public class MrTblPhoneLocation extends BasePo {

	private String mobilePrefix;
	private String province;
	private String city;
	private String operator;
	private String areaCode;
	private String postCode;


	public String getMobilePrefix() { return mobilePrefix; }
	public void setMobilePrefix(String mobilePrefix) { this.mobilePrefix = mobilePrefix; }

	public String getProvince() { return province; }
	public void setProvince(String province) { this.province = province; }

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }

	public String getOperator() { return operator; }
	public void setOperator(String operator) { this.operator = operator; }

	public String getAreaCode() { return areaCode; }
	public void setAreaCode(String areaCode) { this.areaCode = areaCode; }

	public String getPostCode() { return postCode; }
	public void setPostCode(String postCode) { this.postCode = postCode; }



}
