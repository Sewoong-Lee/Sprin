package org.spring.my.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.dao.MemberDAO;
import org.spring.my.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MemberServiceimpl implements MemberService{
	
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	@Autowired
	MailSendService mailSendService;
	@Autowired
	private MemberDAO memberdao;
	@Autowired
	private FileService fileservice;
	
	//암호화위해서 자동주입(DI)
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	@Override
	public Map<String, Object> insert(Member member, MultipartFile file1) throws Exception {
		//중복 아이디 체크
		Map<String, Object> rmap = new HashMap<>();
		//rcode값
		// 0:사용 가능 아이디
		// -1: 중복 아이디
		//msg에 담아 보냄
		//회원이 있는지 조회
		Member rmember = memberdao.selectone(member.getUserid());
		
		if(rmember != null) {
			rmap.put("rcode", -1);
			rmap.put("msg", "중복된 아이디입니다.");
			
			return rmap;
		}
		
			
		//1) 파일 업로드
		String filename = fileservice.fileupload(file1);
		//실제 올려질 파일 이름
		member.setFilename(filename);
		logger.info(member.toString());
			
		//2)비밀번호 암호화
		//member.setPasswd(bCryptPasswordEncoder.encode(member.getPasswd())); //한번에 하기
		String bpasswd = bCryptPasswordEncoder.encode(member.getPasswd());
		member.setPasswd(bpasswd);
			
		logger.info(member.toString());
		
		//3) db저장
		memberdao.insert(member);
		
		//4)확인 이메일 전송
		String autoCode = mailSendService.sendAuthMail(member.getEmail(), member.getUserid());
		
		rmap.put("autoCode", autoCode);
		rmap.put("rcode", 0);
		rmap.put("msg", "회원가입 완료");
		
		return rmap;
	}

	@Override
	public Map<String, Object> login(String userid, String passwd) {
		Map<String, Object> rmap = new HashMap<>();
		//0:로그인 완료 (정상회원)
		//1:아이디 없음
		//2:비밀번호 불일치
		//3:이메일 미인증
		int rcode = 0;
		String msg = null;
		//셀렉트원으로 회원 조회
		Member member = memberdao.selectone(userid);
		
		if(member == null) {
			msg = "아이디가 존재하지 않습니다.";
			rcode = 1;
		}else {
			//이메일 체크
			if(!member.getEmailauth().equals("1")) {  //이메일 인증을 아직 안헀다면
				msg = "이메일 인증을 해주세요";
				rcode = 3;
			}else if(bCryptPasswordEncoder.matches(passwd, member.getPasswd())) {
				//비밀번호 체크(데이터베이스의 비밀번호가 뒤에 있어야함)
				msg = "로그인 완료";
				rcode = 0;
			}else {
				msg = "비밀번호 불일치";
				rcode = 2;
			}
		}
		rmap.put("msg", msg);
		rmap.put("rcode", rcode);
		return rmap;
	}

	@Override
	public Member selectone(String userid) {
		
		return memberdao.selectone(userid);
	}

	@Override
	public void emailauth(String userid) {
		memberdao.emailauth(userid);
		
	}
	
	
}
