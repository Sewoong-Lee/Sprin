package org.spring.my.dao;

import java.util.List;

import org.spring.my.dto.MemberDTO;

public interface MemberDAO {
	//추상메소드
	public void insert(MemberDTO memberdto);

	public List<MemberDTO> selectlist();

	public MemberDTO selectone(String userid);

	public int delet(String userid);

	public int update(MemberDTO mdto);
}
