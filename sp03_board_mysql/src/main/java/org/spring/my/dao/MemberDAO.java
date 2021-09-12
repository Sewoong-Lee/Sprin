package org.spring.my.dao;

import java.util.Map;

import org.spring.my.dto.Member;

public interface MemberDAO {
	
	void insert(Member member);

	Member selectone(String userid);

	void emailauth(String userid);
	
	public void insertnaver(Map<String, String> rsmap);
}
