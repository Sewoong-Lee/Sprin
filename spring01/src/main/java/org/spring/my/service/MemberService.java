package org.spring.my.service;

import java.util.List;

import org.spring.my.dto.MemberDTO;

public interface MemberService {
	
	public void insert(MemberDTO memberdto);

	public List<MemberDTO> list();

	public MemberDTO selectone(String userid);

	public int delet(String userid);

	public int update(MemberDTO mdto);

	
	
}
