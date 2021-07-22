package org.spring.my.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@ToString
@Data
//data만 쓰면 3개 합친게 만들어짐
public class Grade {
	private String ccode; //과목코드
	private String sno; //학번
	private String jcode; //과목코드
	private int score; //점수
	private List<Grade> list;  //점수의 리스트 (과목코드+점수 배열)
	
	
}
