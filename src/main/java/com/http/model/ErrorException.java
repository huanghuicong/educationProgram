package com.http.model;
/**
 * 
 * @ClassName: ErrorMsg.java
 * @Description: 错误对象模型
 * @author huanghc
 * @date 2018年3月19日 下午3:21:27
 */
public class ErrorException {

	//错误码
	private String errorCode;
	/*错误信息*/
	private String errorMessage;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "ServiceException [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
	
}
