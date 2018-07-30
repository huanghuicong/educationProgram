package com.http.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.http.dao.SmsInfoDao;
import com.http.model.SmsInfo;
import com.http.service.SmsInfoService;

@Service
public class SmsInfoServiceImpl implements SmsInfoService {

	@Autowired
	private SmsInfoDao smsInfoDao;
	
	@Override
	public void save(SmsInfo smsInfo) {
		smsInfoDao.insert(smsInfo);
	}

	@Override
	public List<SmsInfo> queryList(SmsInfo smsInfo) {
		return smsInfoDao.queryList(smsInfo);
	}

}
