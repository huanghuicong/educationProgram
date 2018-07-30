package com.http.model;

/**
 * 
 * @ClassName: WeChatUserFormId.java
 * @Description: 用户formid关系表
 * @author huanghc
 * @date 2018年5月8日 下午3:21:27
 */
public class WeChatUserFormId {

	private Long id;
	/*唯一标识*/
	private String uuid;
	/*微信账号id*/
	private String openId;
	/*微信表单id*/
	private String formId;
	/*消息内容*/
	private String message;
	/*通知时间*/
	private String sendTime;
	/*小程序通知消息模板id*/
	private String templateId;
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
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
	@Override
	public String toString() {
		return "WeChatUserFormId [id=" + id + ", uuid=" + uuid + ", openId=" + openId + ", formId=" + formId
				+ ", message=" + message + ", sendTime=" + sendTime + ", templateId=" + templateId + ", failureTime="
				+ failureTime + ", createTime=" + createTime + ", operateTime=" + operateTime + ", state=" + state
				+ "]";
	}
	
	
}
