package com.http.dao;

import java.util.List;

import com.http.model.SmsInfo;

public interface SmsInfoDao extends BaseDao<SmsInfo>{

	public List<SmsInfo> queryList(SmsInfo smsInfo);
}
