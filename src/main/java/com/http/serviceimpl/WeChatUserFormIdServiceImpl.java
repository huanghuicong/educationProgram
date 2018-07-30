package com.http.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.http.dao.WeChatUserFormIdDao;
import com.http.model.WeChatUserFormId;
import com.http.service.WeChatUserFormIdService;

@Service
public class WeChatUserFormIdServiceImpl implements WeChatUserFormIdService {

	@Autowired
	private WeChatUserFormIdDao weChatUserFormIdDao;
	
	@Override
	public void save(WeChatUserFormId weChatUserFormId) {
		weChatUserFormIdDao.insert(weChatUserFormId);
	}

	@Override
	public List<WeChatUserFormId> queryExpireFormIdList(WeChatUserFormId weChatUserFormId) {
		return weChatUserFormIdDao.queryExpireFormIdList(weChatUserFormId);
	}

	@Override
	public void updateExpireFormIdList(WeChatUserFormId weChatUserFormId) {
	    weChatUserFormIdDao.updateExpireFormIdList(weChatUserFormId);
	}

	@Override
	public List<WeChatUserFormId> queryObjectFormIdList(WeChatUserFormId weChatUserFormId) {
		return weChatUserFormIdDao.queryObjectFormIdList(weChatUserFormId);
	}

	@Override
	public void updateExpireFormIdByUuid(WeChatUserFormId weChatUserFormId) {
		weChatUserFormIdDao.updateExpireFormIdByUuid(weChatUserFormId);
	}

}
