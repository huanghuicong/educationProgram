package com.http.model;

/**
 * 
 * @ClassName: CurriculumScheduleTask.java
 * @Description: 课程日程信息定时任务模板信息
 * @author huanghc
 * @date 2018年4月19日 下午3:21:27
 */
public class CurriculumScheduleTask {

	private Long id;
	/*唯一标识*/
	private String uuid;
	/*日程关联id*/
	private Long scheduleId;
	/*课程关联id*/
	private Long curriculumId;
	/*课程名字*/
	private String curriculumName;
	/*用户的openId*/
	private String toUser;
	/*小程序通知消息模板id*/
	private String templateId;
	/*表单id-通知消息权限*/
    private String formId;
    /*消息内容*/
    private String message;
    /*地点*/
    private String place;
    /*课程开始时间*/
    private String startTime;
    /*课程结束时间*/
    private String endTime;
    /*发送时间*/
    private String sendTime;
    /*创建时间*/
	private String createTime;
	/*操作时间*/
	private String operateTime;
	/*状态*/
	private Integer state;
	/*操作uuid*/
	private String operateUuid;
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
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Long getCurriculumId() {
		return curriculumId;
	}
	public void setCurriculumId(Long curriculumId) {
		this.curriculumId = curriculumId;
	}
	public String getCurriculumName() {
		return curriculumName;
	}
	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
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
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
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
	public String getOperateUuid() {
		return operateUuid;
	}
	public void setOperateUuid(String operateUuid) {
		this.operateUuid = operateUuid;
	}
	@Override
	public String toString() {
		return "CurriculumScheduleTask [id=" + id + ", uuid=" + uuid + ", scheduleId=" + scheduleId + ", curriculumId="
				+ curriculumId + ", curriculumName=" + curriculumName + ", toUser=" + toUser + ", templateId="
				+ templateId + ", formId=" + formId + ", message=" + message + ", place=" + place + ", startTime="
				+ startTime + ", endTime=" + endTime + ", sendTime=" + sendTime + ", createTime=" + createTime
				+ ", operateTime=" + operateTime + ", state=" + state + ", operateUuid=" + operateUuid + "]";
	}
	
	
}
