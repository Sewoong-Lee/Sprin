package org.spring.my.controller;

import java.util.HashSet;

public class test {

	public static void main(String[] args) {
		System.out.println("테스트 하는거 시작");
		
		//1~100까지 더하기
		int sum = 0;
		for(int i=0; i <= 100; i++) {
			sum += i;
		};
		System.out.println(sum);
		
		//포문 구구단 만들기
		for(int i=1; i<10; i++) {
			System.out.println(i +" 단");
			for(int a=1; a<10; a++) {
				System.out.println(i +" * "+ a + " ="+ i*a);
			};
		};
		
		//구구단 3단에서 4의 배수 제외하고 출력
		for(int i=0; i<10; i++) {
			if(!((i*3)%4 == 0)) {
				System.out.println("4의 배수가 아니야"+i*3);
			}else {
				System.out.println("4의 배수"+i*3);
			}
		}
		
		//로또번호 추출
		// Integer 정수 객체를 담는 HashSet
		// HashSet은 중복을 거부하는 Set 입니다.
		// 중복이 들어오면 이는 무시되어 같은 수는 하나만 담기게 됩니다.
		HashSet<Integer> luckyNumbers = new HashSet<>();
		
		// 이 HashSet에 수가 6개 채워질 때 까지 반복합니다.
		// 중복 처리가 자동으로 이루어지므로 삽입만 하면 됩니다.
		// 하지만 중복된 수는 삽입이 거부되므로 반복 횟수가 늘어날 수 있습니다.
		while(luckyNumbers.size() < 6){
		    // 1. 임의의 수 삽입
		    // Math.random()이 제공하는 0.0~0.999...의 수에 45를 곱해
		    // 0.0~44.xx의 수를 만들고 이를 정수로 바꾼다.
		    // 여기에 1을 더해서 1~45가 되도록 한다.
		    luckyNumbers.add((int)(Math.random() * 45) + 1);
		}
		 
		// 2. 출력하기
		System.out.println("행운의 번호는 아래와 같습니다:");
//		boolean isFirstNum = true; // 맨 처음에 기재되는 번호인지를 파악하는 플래그
//		// luckyNumbers 각 원소를 읽기전용으로 하나씩 꺼내기
		for(int luckyNum : luckyNumbers){
//		    if(isFirstNum){
//		        isFirstNum = false; // 맨 처음에는 ", " 문자열을 추가하지 않고 플래그를 내립니다.
//		    } else {
//		        // 맨 처음 원소가 아니므로 앞에 ", "문자열 추가
//		        // 1, 2, 3, ..., 6 꼴로 나오게 함
//		        System.out.print(", ");
//		    }
		    System.out.print(luckyNum+" ");
		}


		
		
//		int[] ball = new int[45]; //로또 공 45개 생성
//		
//		for(int i=0; i<ball.length; i++) {
//			ball[i] = i+1;
//		}
//		int[] tmp = new int[6];
//		int lotto = 0; //랜덤한 로또 공 번호를 위해 생성
//		for(int i=0; i<6; i++) {
//			lotto = (int) (Math.random() *45); //0~44 임의의 값을 구함
//			tmp[i] = lotto;
//			System.out.println("/"+ball[tmp[i]]);
//		}

		
//		for(int i=0; i<6; i++) {
//			System.out.println(ball[i] + " / ");
//		}
		
//		int[] ball = new int[45]; //로또 공 45개 생성
//		
//		for(int i=0; i<ball.length; i++) {
//			ball[i] = i+1;
//		}
//		int tmp = 0; //shuffle을 위한 빈 공간 생성
//		int lotto = 0; //랜덤한 로또 공 번호를 위해 생성
//		for(int i=0; i<45; i++) {
//			lotto = (int) (Math.random() *45); //0~44 임의의 값을 구함
//			tmp = ball[0];
//			ball[i] = ball[lotto];
//			ball[lotto] = tmp;
//			System.out.println(i+" 번 "+ball[i] + " / ");
//		}
////		for(int i=0; i<6; i++) {
////			System.out.println(ball[i] + " / ");
////		}
		
		
	}

}
