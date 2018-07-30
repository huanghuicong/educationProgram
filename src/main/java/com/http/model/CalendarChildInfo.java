package com.http.model;

/**
 * 
 * @ClassName: CalendarChildInfo.java
 * @Description: 日历用户孩子信息
 * @author huanghc
 * @date 2018年5月8日 下午3:21:27
 */
public class CalendarChildInfo {

	private Long id;
	/*唯一标识*/
	private String uuid;
	/*微信账号id*/
	private String openId;
	/*类别：1，男孩；2：女孩*/
	private Integer childType;
	/*姓名*/
	private String childName;
	/*年龄*/
	private Integer childAge;
	/*头像*/
	private String childHeadPortrait;
	/*创建时间*/
	private String createTime;
	/*操作时间*/
	private String operateTime;
	/*状态*/
	private Integer state;
	/*微信账户关联手机号*/
	private String weChatMobile;
	/*男孩数量*/
	private Integer BoyNum;
	/*女孩数量*/
	private Integer girlNum;
	/*短信验证码唯一标识*/
	private String smsUuid;
	/*短信验证码*/
	private String smsCode;
	/*最后一次切换的孩子*/
	private String lastOperate;
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
	public Integer getChildType() {
		return childType;
	}
	public void setChildType(Integer childType) {
		this.childType = childType;
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
	public String getWeChatMobile() {
		return weChatMobile;
	}
	public void setWeChatMobile(String weChatMobile) {
		this.weChatMobile = weChatMobile;
	}
	public Integer getBoyNum() {
		return BoyNum;
	}
	public void setBoyNum(Integer boyNum) {
		BoyNum = boyNum;
	}
	public Integer getGirlNum() {
		return girlNum;
	}
	public void setGirlNum(Integer girlNum) {
		this.girlNum = girlNum;
	}
	public String getSmsUuid() {
		return smsUuid;
	}
	public void setSmsUuid(String smsUuid) {
		this.smsUuid = smsUuid;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public String getLastOperate() {
		return lastOperate;
	}
	public void setLastOperate(String lastOperate) {
		this.lastOperate = lastOperate;
	}
	@Override
	public String toString() {
		return "CalendarChildInfo [id=" + id + ", uuid=" + uuid + ", openId=" + openId + ", childType=" + childType
				+ ", childName=" + childName + ", childAge=" + childAge + ", childHeadPortrait=" + childHeadPortrait
				+ ", createTime=" + createTime + ", operateTime=" + operateTime + ", state=" + state + ", weChatMobile="
				+ weChatMobile + ", BoyNum=" + BoyNum + ", girlNum=" + girlNum + ", smsUuid=" + smsUuid + ", smsCode="
				+ smsCode + ", lastOperate=" + lastOperate + "]";
	}
	
	
	
	
}
