package com.http.model;

/**
 * 
 * @ClassName: CalendarCurriculumInfo.java
 * @Description: 日历课程信息
 * @author huanghc
 * @date 2018年4月19日 下午3:21:27
 */
public class CalendarCurriculumInfo {

	private Long id;
	/*唯一标识*/
	private String uuid;
	/*孩子关联id*/
	private Long calendarChildId;
	/*课程名字*/
	private String curriculumName;
	/*1,默认;2,其他*/
	private String curriculumType;
	/*课程标签1，语文；2，数学；3，英语；4，其他*/
	private String curriculumTag;
	/*课程学校名字*/
	private String schoolName;
	/*提醒时间*/
	private String remindingTime;
	/*创建时间*/
	private String createTime;
	/*操作时间*/
	private String operateTime;
	/*状态*/
	private Integer state;
	/*姓名*/
	private String childName;
	/*年龄*/
	private Integer childAge;
	/*头像*/
	private String childHeadPortrait;
	/*删除类型*/
	private String deleteType;
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
	public Long getCalendarChildId() {
		return calendarChildId;
	}
	public void setCalendarChildId(Long calendarChildId) {
		this.calendarChildId = calendarChildId;
	}
	public String getCurriculumName() {
		return curriculumName;
	}
	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}
	public String getCurriculumType() {
		return curriculumType;
	}
	public void setCurriculumType(String curriculumType) {
		this.curriculumType = curriculumType;
	}
	public String getCurriculumTag() {
		return curriculumTag;
	}
	public void setCurriculumTag(String curriculumTag) {
		this.curriculumTag = curriculumTag;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getRemindingTime() {
		return remindingTime;
	}
	public void setRemindingTime(String remindingTime) {
		this.remindingTime = remindingTime;
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
	public String getDeleteType() {
		return deleteType;
	}
	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}
	@Override
	public String toString() {
		return "CalendarCurriculumInfo [id=" + id + ", uuid=" + uuid + ", calendarChildId=" + calendarChildId
				+ ", curriculumName=" + curriculumName + ", curriculumType=" + curriculumType + ", curriculumTag="
				+ curriculumTag + ", schoolName=" + schoolName + ", remindingTime=" + remindingTime + ", createTime="
				+ createTime + ", operateTime=" + operateTime + ", state=" + state + ", childName=" + childName
				+ ", childAge=" + childAge + ", childHeadPortrait=" + childHeadPortrait + ", deleteType=" + deleteType
				+ "]";
	}
	
}
