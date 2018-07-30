package com.http.service;

import java.util.List;
import java.util.Map;

import com.http.model.AccessToken;

public interface AccessTokenService {

	public void save(AccessToken accessToken);
	
	public List<AccessToken> queryList(Map<String, Object> params);
}
