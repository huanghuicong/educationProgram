package com.http.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.http.dao.AccessTokenDao;
import com.http.model.AccessToken;
import com.http.service.AccessTokenService;

@Service
@Transactional(isolation=Isolation.DEFAULT)
public class AccessTokenServiceImpl implements AccessTokenService{

	@Autowired
	private AccessTokenDao accessTokenDao;
	@Override
	public void save(AccessToken accessToken) {
		accessTokenDao.insert(accessToken);
	}
	@Override
	public List<AccessToken> queryList(Map<String, Object> params) {
		return accessTokenDao.queryList(params);
	}

}
