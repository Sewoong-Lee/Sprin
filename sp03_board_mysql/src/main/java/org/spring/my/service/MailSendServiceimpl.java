package org.spring.my.service;

import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailSendServiceimpl implements MailSendService{
	
	//root-context.xml에서 만들어진 객체 자동 주입
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	//이메일 인증키 생성
	private String getAuthCode() { //아래에서만 쓸거니까 프라이빗
		StringBuffer authCode = new StringBuffer();
		Random random = new Random();
		for(int i = 0 ; i < 6; i++) {
			authCode.append(random.nextInt(10));
		}
		
		return authCode.toString();
	}
	
	//이메일 확인 전송
	@Override
	public String sendAuthMail(String email, String userid) throws Exception {
		//6자리 난수 인증 번호 생성
		String authCode = getAuthCode();
		//보낼 메일 내용
		StringBuffer mailContent = new StringBuffer();
		mailContent.append("<h2>회원사입 확인 메일</h2>");
		mailContent.append(userid+"님 반갑습니다 아래 링크 클릭<br>");
		mailContent.append("<a href='http://localhost:8081/my/member/joinConfirm?userid="+userid+"&authCode="+authCode+"'>이메일 인증</a>");
		
		
		//보낼메일 객체 생성
		MimeMessage mimeMsg = mailSender.createMimeMessage();
		mimeMsg.setSubject("[이메일 인증 확인] 테스트", "utf-8");
		mimeMsg.setText(mailContent.toString(), "utf-8", "html");
		mimeMsg.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); //보낼곳 지정
		
		//메일 보내기
		mailSender.send(mimeMsg);
		
		return authCode;
	}

}
