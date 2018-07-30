package com.http.service;

import java.util.List;

import com.http.model.SmsInfo;

public interface SmsInfoService {

	public void save(SmsInfo smsInfo);
	public List<SmsInfo> queryList(SmsInfo smsInfo);
}
