package org.spring.my.service;

import java.util.Map;

public interface NaverService {
	
	public Map<String, String> getapiURL() throws Exception;
	//접근 토큰 얻기
	public String getNaverToken(String code, String state) throws Exception;
	//유저 정보 얻기
	public Map<String, String> getNaverUserInfo(String access_token) throws Exception;
	//회원가입
	public void insert(Map<String, String> rsmap);
}
