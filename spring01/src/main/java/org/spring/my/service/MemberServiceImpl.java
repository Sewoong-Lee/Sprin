package org.spring.my.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.controller.MemberController;
import org.spring.my.dao.MemberDAO;
import org.spring.my.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	//컴파일시점에서 인터페이스 참조
	//런타임시점에서 상속받은 객체(MemberDAOImpl)를 자동 주입
	//다형성에 의해서 자동주입 가능
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public void insert(MemberDTO memberdto) {
		
		memberDAO.insert(memberdto);
		
	}

	@Override
	public List<MemberDTO> list() {
		
		return memberDAO.selectlist();
	}

	@Override
	public MemberDTO selectone(String userid) {
		System.out.println(userid);
		//return memberDAO.selectone();
		return memberDAO.selectone(userid);
	}

	@Override
	public int delet(String userid) {
		
		return memberDAO.delet(userid);
	}

	@Override
	public int update(MemberDTO mdto) {
		//새로운 비밀번호가 입력이 되었을때 기존 비밀번호를 새로운 비밀번호로 넣는다
		if(!mdto.getNewpasswd().equals("")) {
			mdto.setPasswd(mdto.getNewpasswd());
		}
		logger.info(mdto.toString());
		return memberDAO.update(mdto);
	}
	
	
	
	
}
