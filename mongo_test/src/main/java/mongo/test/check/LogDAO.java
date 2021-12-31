package mongo.test.check;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDAO {
	
	//자동으로 DI를 적용하기 위한 어노테이션 : @Inject, @Autowired
	@Inject
	private MongoTemplate mongoTemplate;
	
	//테스트용 메서드
	public MongoTemplate getTemplate () {
		return mongoTemplate;
	}
	public void saveLog() {
		System.out.println("LogDAO.saveLog().mongoTemplate" + mongoTemplate);
	}
	
}
