package com.cos.chatapp;

//public interface ChatRepository extends ReactiveMongoRepository<Chat, String> {
//	
//	@Tailable
//	@Query("{ sender : ?0, receiver: ?1 }")
//	Flux<Chat> mFindBySender(String sender, String receiver);
//	
////	//여러개의 누적되 데이터를 한번에 처리하기 위하여 sse 프로토콜 사용
////	
////	@Tailable //커서를 안닫고 계속 유지 
////	@Query("{sender:?0, receiver:?1}")
////	Flux<Chat> mFindBySender(String sender, String receiver);
////	//Flux 흐름 / 데이터를 계속 받겠다. / 리스펀스를 유지하면서 데이터를 계속 흘려보내기
////	//위 쿼리가 동작을 하면 위의 데이터를 찾겠다는 뜻
//}
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import reactor.core.publisher.Flux;


public interface ChatRepository extends ReactiveMongoRepository<Chat, String>{
	
	@Tailable
	@Query("{ sender : ?0, receiver: ?1 }")
	Flux<Chat> mFindBySender(String sender, String receiver);
	
//	@Tailable
//	@Query("{ sender : ?0, receiver: ?1 }")
//	public Flux<Chat> getaa(String sender, String receiver) {
//		return mFindBySender(sender, receiver);
//	}
	
	
	
}