package org.spring.my.service;

public interface MailSendService {
	public String sendAuthMail(String email, String userid) throws Exception;
}
