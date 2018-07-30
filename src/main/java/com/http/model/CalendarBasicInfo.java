package com.http.model;

/**
 * 
 * @ClassName: CalendarBasicInfo.java
 * @Description: 日历用户基础信息
 * @author huanghc
 * @date 2018年4月19日 下午3:21:27
 */
public class CalendarBasicInfo {

	private Long id;
	/*唯一标识*/
	private String uuid;
	/*微信账号id*/
	private String openId;
	/*微信头像*/
	private String weChatHeadPortrait;
	/*微信用户名*/
	private String weChatName;
	/*微信绑定手机号*/
	private String weChatMobile;
	/*创建时间*/
	private String createTime;
	/*操作时间*/
	private String operateTime;
	/*状态*/
	private Integer state;
	/*短信验证码唯一标识*/
	private String smsUuid;
	/*短信验证码*/
	private String smsCode;
	/*用户标识*/
	private String jsCode;
	/*解密数据*/
	private String encrypData;
	/*偏移量*/
	private String ivData;
	/*用户定位地址*/
	private String locationAddress;
	/*最后登录时间*/
	private String lastLoginTime;
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
	public String getWeChatHeadPortrait() {
		return weChatHeadPortrait;
	}
	public void setWeChatHeadPortrait(String weChatHeadPortrait) {
		this.weChatHeadPortrait = weChatHeadPortrait;
	}
	public String getWeChatName() {
		return weChatName;
	}
	public void setWeChatName(String weChatName) {
		this.weChatName = weChatName;
	}
	public String getWeChatMobile() {
		return weChatMobile;
	}
	public void setWeChatMobile(String weChatMobile) {
		this.weChatMobile = weChatMobile;
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
	public String getJsCode() {
		return jsCode;
	}
	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}
	public String getEncrypData() {
		return encrypData;
	}
	public void setEncrypData(String encrypData) {
		this.encrypData = encrypData;
	}
	public String getIvData() {
		return ivData;
	}
	public void setIvData(String ivData) {
		this.ivData = ivData;
	}
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	@Override
	public String toString() {
		return "CalendarBasicInfo [id=" + id + ", uuid=" + uuid + ", openId=" + openId + ", weChatHeadPortrait="
				+ weChatHeadPortrait + ", weChatName=" + weChatName + ", weChatMobile=" + weChatMobile + ", createTime="
				+ createTime + ", operateTime=" + operateTime + ", state=" + state + ", smsUuid=" + smsUuid
				+ ", smsCode=" + smsCode + ", jsCode=" + jsCode + ", encrypData=" + encrypData + ", ivData=" + ivData
				+ ", locationAddress=" + locationAddress + ", lastLoginTime=" + lastLoginTime + "]";
	}
	
}
