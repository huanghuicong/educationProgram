package com.http.model;

import java.util.List;

/** 
 * <p> 
 * 返回模型
 * </p> 
 * 
 * @since 2018-03-09
 * @user: huanghc 
 */ 
public class ResponseBaseModel {

	//消息返回时间
	private String resultTime;
	//系统版本
	private String System;
	//会话id
	private String sessionId;
	//返回参数模型
	private Object resultObject;
	private List<?> resultList;
	public String getResultTime() {
		return resultTime;
	}
	public void setResultTime(String resultTime) {
		this.resultTime = resultTime;
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
	public Object getResultObject() {
		return resultObject;
	}
	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}
	public List<?> getResultList() {
		return resultList;
	}
	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}
	@Override
	public String toString() {
		return "ResponseBaseModel [resultTime=" + resultTime + ", System=" + System + ", sessionId=" + sessionId
				+ ", resultObject=" + resultObject + ", resultList=" + resultList + "]";
	}
	
}
