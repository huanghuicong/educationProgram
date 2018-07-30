package com.http.model;

/**
 * 
 * @ClassName: CurriculumScheduleInfo.java
 * @Description: 课程日程信息
 * @author huanghc
 * @date 2018年4月19日 下午3:21:27
 */
public class CurriculumScheduleInfo {

	private Long id;
	/*唯一标识*/
	private String uuid;
	/*课程关联id*/
	private Long curriculumId;
	/*课程名称*/
	private String curriculumName;
	/*提醒开始传入日期*/
	private String firstTime;
	/*星期几*/
	private String weekDay;
	/*开始时间*/
	private String startTime;
	/*结束时间*/
	private String endTime;
	/*提醒时间*/
	private String remindingTime;
	/*提醒类型 每周固定时刻|就这一次*/
	private String remindingType;
	/*成绩*/
	private String score;
	/*地点*/
	private String place;
	/*备忘记录*/
	private String memorandum;
	/*创建时间*/
	private String createTime;
	/*操作时间*/
	private String operateTime;
	/*状态*/
	private Integer state;
	/*用户的openId*/
	private String toUser;
	/*小程序通知消息模板id*/
	private String templateId;
	/*表单id-通知消息权限*/
    private String formId;
    
    private Long childId;

    /*操作uuid*/
    private String operateUuid;
    /*删除类型*/
    private String deleteType;
    /*开始小时*/
    private String startHour;
    /*结束小时*/
    private String endHour;
    
    /*课程标签1，语文；2，数学；3，英语；4，其他*/
	private String curriculumTag;
	/*1,默认;2,其他*/
	private String curriculumType;
	/*姓名*/
	private String childName;
	/*年龄*/
	private Integer childAge;
	/*头像*/
	private String childHeadPortrait;
	/*页码*/
	private Integer page;
	/*每一页条数*/
	private Integer pageNum;
	/*开始条数*/
	private Integer startNum;
	/*日程次数*/
	private Integer scheduleInfoNum;
	/*日程颜色*/
	private String scheduleInfoColor;
	/*每隔多少天*/
	private Integer scheduleInfoDay;
	/*是否创建底部标签0，是；1，否*/
	private String isCreateCurriculum;
	/*剩余节数*/
	private Integer sumScheduleInfoNum;
	/*定位地址*/
	private String locationAddress;
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
	public String getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
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
	public String getRemindingTime() {
		return remindingTime;
	}
	public void setRemindingTime(String remindingTime) {
		this.remindingTime = remindingTime;
	}
	public String getRemindingType() {
		return remindingType;
	}
	public void setRemindingType(String remindingType) {
		this.remindingType = remindingType;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getMemorandum() {
		return memorandum;
	}
	public void setMemorandum(String memorandum) {
		this.memorandum = memorandum;
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
	public Long getChildId() {
		return childId;
	}
	public void setChildId(Long childId) {
		this.childId = childId;
	}
	public String getOperateUuid() {
		return operateUuid;
	}
	public void setOperateUuid(String operateUuid) {
		this.operateUuid = operateUuid;
	}
	public String getDeleteType() {
		return deleteType;
	}
	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getCurriculumTag() {
		return curriculumTag;
	}
	public void setCurriculumTag(String curriculumTag) {
		this.curriculumTag = curriculumTag;
	}
	public String getCurriculumType() {
		return curriculumType;
	}
	public void setCurriculumType(String curriculumType) {
		this.curriculumType = curriculumType;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public Integer getChildAge() {
		return childAge;
	}
	public void setChildAge(Integer childAge) {
		this.childAge = childAge;
	}
	public String getChildHeadPortrait() {
		return childHeadPortrait;
	}
	public void setChildHeadPortrait(String childHeadPortrait) {
		this.childHeadPortrait = childHeadPortrait;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getStartNum() {
		return startNum;
	}
	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	public Integer getScheduleInfoNum() {
		return scheduleInfoNum;
	}
	public void setScheduleInfoNum(Integer scheduleInfoNum) {
		this.scheduleInfoNum = scheduleInfoNum;
	}
	public String getScheduleInfoColor() {
		return scheduleInfoColor;
	}
	public void setScheduleInfoColor(String scheduleInfoColor) {
		this.scheduleInfoColor = scheduleInfoColor;
	}
	public Integer getScheduleInfoDay() {
		return scheduleInfoDay;
	}
	public void setScheduleInfoDay(Integer scheduleInfoDay) {
		this.scheduleInfoDay = scheduleInfoDay;
	}
	public String getIsCreateCurriculum() {
		return isCreateCurriculum;
	}
	public void setIsCreateCurriculum(String isCreateCurriculum) {
		this.isCreateCurriculum = isCreateCurriculum;
	}
	public Integer getSumScheduleInfoNum() {
		return sumScheduleInfoNum;
	}
	public void setSumScheduleInfoNum(Integer sumScheduleInfoNum) {
		this.sumScheduleInfoNum = sumScheduleInfoNum;
	}
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}
	@Override
	public String toString() {
		return "CurriculumScheduleInfo [id=" + id + ", uuid=" + uuid + ", curriculumId=" + curriculumId
				+ ", curriculumName=" + curriculumName + ", firstTime=" + firstTime + ", weekDay=" + weekDay
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", remindingTime=" + remindingTime
				+ ", remindingType=" + remindingType + ", score=" + score + ", place=" + place + ", memorandum="
				+ memorandum + ", createTime=" + createTime + ", operateTime=" + operateTime + ", state=" + state
				+ ", toUser=" + toUser + ", templateId=" + templateId + ", formId=" + formId + ", childId=" + childId
				+ ", operateUuid=" + operateUuid + ", deleteType=" + deleteType + ", startHour=" + startHour
				+ ", endHour=" + endHour + ", curriculumTag=" + curriculumTag + ", curriculumType=" + curriculumType
				+ ", childName=" + childName + ", childAge=" + childAge + ", childHeadPortrait=" + childHeadPortrait
				+ ", page=" + page + ", pageNum=" + pageNum + ", startNum=" + startNum + ", scheduleInfoNum="
				+ scheduleInfoNum + ", scheduleInfoColor=" + scheduleInfoColor + ", scheduleInfoDay=" + scheduleInfoDay
				+ ", isCreateCurriculum=" + isCreateCurriculum + ", sumScheduleInfoNum=" + sumScheduleInfoNum
				+ ", locationAddress=" + locationAddress + "]";
	}
	
}
