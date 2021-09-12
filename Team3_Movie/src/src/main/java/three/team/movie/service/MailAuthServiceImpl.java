package three.team.movie.service;

import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailAuthServiceImpl implements MailAuthService {

	@Autowired
	private JavaMailSenderImpl mailSender;
	
	public String getAuthCode() {
		StringBuffer authCode = new StringBuffer();
		Random random = new Random();
		for (int i = 0 ; i<6 ; i++) {
			int code = random.nextInt(10);
			authCode.append(code);
		}
		return authCode.toString();
	}
	
	@Override
	public String sendMail(String email) throws Exception {
		String authCode = getAuthCode();
		StringBuffer mailContent = new StringBuffer();
		mailContent.append("<h2>CCV 가입을 위한 메일 인증을 진행해주세요.</h2>");
		mailContent.append("<a href ='http://localhost:8081/movie/codeCheck");
		mailContent.append("?email="+email+"&authCode="+authCode+"'>이 곳을 클릭해주세요.</a>");
		
		//메일 보낼 객체 생성
		MimeMessage mimeMail = mailSender.createMimeMessage();
		//메일 제목
		mimeMail.setSubject("[당신의 취향에 가장 완벽한 선택, CCV]", "UTF-8");
		
		mimeMail.setText(mailContent.toString(), "UTF-8", "html");
		mimeMail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		
		mailSender.send(mimeMail);
		return authCode;
	}

	
}
