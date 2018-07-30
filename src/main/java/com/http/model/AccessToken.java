package com.http.model;

/** 
 * 微信通用接口凭证 
 *  
 * @date 2013-08-08 
 */  
public class AccessToken {  
	private Integer id;
    // 获取到的凭证  
    private String token;  
    // 凭证有效时间，单位：秒  
    private int expiresIn; 
    // 公总号id
    private String appId;
    // 创建时间
    private String createTime;
    // 状态
    private Integer state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}  
