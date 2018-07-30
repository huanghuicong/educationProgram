package com.http.model;

/** 
 * <p> 
 * 请求模型
 * </p> 
 * 
 * @since 2018-03-09
 * @user: huanghc 
 */  
public class RequestBaseModel {

	//消息发送时间
	private String time;
	//系统版本
	private String System;
	//会话id
	private String sessionId;
	//参数列表
	private String params;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSystem() {
		return System;
	}
	public void setSystem(String system) {
		System = system;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	@Override
	public String toString() {
		return "BaseModel [time=" + time + ", System=" + System + ", sessionId=" + sessionId + ", params=" + params
				+ "]";
	}
	
}
