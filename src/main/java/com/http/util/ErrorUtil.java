package com.http.util;

import com.alibaba.fastjson.JSON;
import com.http.model.ErrorException;
import com.http.util.ErrorMsg.MobileService;
/**
 * 
 * @ClassName: ErrorMsg.java
 * @Description: 错误信息返回
 * @author huanghc
 * @date 2018年3月19日 下午3:21:27
 */
public class ErrorUtil {

	public static String errorToMessage(String errorCode){
		ErrorException errorException = new ErrorException();
		errorException.setErrorCode(errorCode);
		errorException.setErrorMessage(MobileService.getMsgByCode(errorCode));
		return JSON.toJSONString(errorException);
	}
}
