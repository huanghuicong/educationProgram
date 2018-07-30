package com.http.dao;

import java.util.List;

import com.http.model.WeChatUserFormId;

public interface WeChatUserFormIdDao extends BaseDao<WeChatUserFormId>{

	public List<WeChatUserFormId> queryExpireFormIdList(WeChatUserFormId weChatUserFormId);
	
	public List<WeChatUserFormId> queryObjectFormIdList(WeChatUserFormId weChatUserFormId);
	
	public void updateExpireFormIdList(WeChatUserFormId weChatUserFormId);
	
	public void updateExpireFormIdByUuid(WeChatUserFormId weChatUserFormId);
}
