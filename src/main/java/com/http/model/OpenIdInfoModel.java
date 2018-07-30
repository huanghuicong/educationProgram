package com.http.model;

/*
 * 获取openid对象信息
 * */
public class OpenIdInfoModel {
    /*小程序唯一标识*/
	private String appid;
	/*小程序的 app secret*/
	private String secret;
	/*登录时获取的 code*/
	private String jsCode;
	/*填写为 authorization_code*/
	private String grantType;
	/*用户openid*/
	private String openId;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getJsCode() {
		return jsCode;
	}
	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Override
	public String toString() {
		return "OpenIdModel [appid=" + appid + ", secret=" + secret + ", jsCode=" + jsCode + ", grantType=" + grantType
				+ ", openId=" + openId + "]";
	}
	
}
