package com.http.model;
/*
 * 短信发送模板
 * */
public class SmsInfo {

	private Long id;
	/*唯一主键*/
	private String uuid;
	/*手机号*/
	private String mobile;
	/*短信签名-可在短信控制台中找到*/
	private String signName;
	/*短信模板-可在短信控制台中找到*/
	private String templateCode;
    /*短信验证码*/
	private String smsCode;
	/*短信内容*/
	private String smsMessage;
	/*发送时间*/
	private String sendTime;
	/*失效时间*/
	private String failureTime;
	/*创建时间*/
	private String createTime;
	/*操作时间*/
	private String operateTime;
	/*状态*/
	private Integer state;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSignName() {
		return signName;
	}
	public void setSignName(String signName) {
		this.signName = signName;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public String getSmsMessage() {
		return smsMessage;
	}
	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getFailureTime() {
		return failureTime;
	}
	public void setFailureTime(String failureTime) {
		this.failureTime = failureTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
