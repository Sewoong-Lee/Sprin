package org.spring.my.dao;

import org.spring.my.dto.Member;

public interface MemberDAO {
	
	void insert(Member member);

	Member selectone(String userid);
}
