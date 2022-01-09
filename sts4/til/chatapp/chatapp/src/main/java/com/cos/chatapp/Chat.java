package com.cos.chatapp;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;



//컬렉션 이름 정의
@Data
@Document(collection = "chat")
public class Chat {
	
	@Id
	private String id;
	private String msg; 
	private String sender; //보내는사람
	private String receiver; //받는사람 (귓속말 시 필요)
	private Integer roomNum; //방 번호
	
	private LocalDateTime createdAt; //시간

	
	

	
	
}
