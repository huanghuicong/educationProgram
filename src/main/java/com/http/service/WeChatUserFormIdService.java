package com.http.service;

import java.util.List;

import com.http.model.WeChatUserFormId;

public interface WeChatUserFormIdService {

	public void save(WeChatUserFormId weChatUserFormId);
	public List<WeChatUserFormId> queryExpireFormIdList(WeChatUserFormId weChatUserFormId);
	public List<WeChatUserFormId> queryObjectFormIdList(WeChatUserFormId weChatUserFormId);
	public void updateExpireFormIdList(WeChatUserFormId weChatUserFormId);
	public void updateExpireFormIdByUuid(WeChatUserFormId weChatUserFormId);
}
