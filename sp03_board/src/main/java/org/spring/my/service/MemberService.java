package org.spring.my.service;

import java.util.Map;

import org.spring.my.dto.Member;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {

	public Map<String, Object> insert(Member member, MultipartFile file1);

	public Map<String, Object> login(String userid, String passwd);

	public Member selectone(String userid);


}
