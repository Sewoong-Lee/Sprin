package mongo.trry.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Repository
public class DAOimpl implements DAO {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void insertuserdao(Map<String, Object> userinfo) {
		System.out.println("인서트 dao 진입");
		mongoTemplate.insert(userinfo,"testdb"); //인서트 데이터, 컬렉션네임(생략 가능)
		
		System.out.println("인서트 dao");
	}

	@Override
	public void removemongo(String key, String value) {
		System.out.println("removemongo dao 진입");
		System.out.println(value);
		Query query = new Query(new Criteria("name").is(value));
		
//		Criteria criteria = new Criteria(key);
//        criteria.is(value);
//		
//		Query query = new Query(criteria);
        
		mongoTemplate.remove(query, "testdb");
		
		System.out.println("removemongo dao 완료");	
	}
	
	@Override
	public void updatemongo(Map<String, Object> value) {
		System.out.println("update dao 진입");
		System.out.println(value);
		//Query query = new Query(new Criteria("name").is(value));
		
		Criteria criteria = new Criteria("name");
        criteria.is(value.get("orgname"));
        
        Query query = new Query(criteria);
        
        Update update = new Update();
        update.set("name", value.get("updatename"));
        update.set("age", value.get("updateage"));
        
        mongoTemplate.updateMulti(query, update, "testdb");  
		
		System.out.println("update dao 완료");	
	}
	
	@Override
	public List<MongoDTO> findmongo(String name) {
		System.out.println("List dao 진입");
		System.out.println(name);
		//Query query = new Query(new Criteria("name").is(value));
		
		Criteria criteria = new Criteria("name");
        criteria.is(name);
        
        Query query = new Query(criteria);
        
        List<MongoDTO> map =  mongoTemplate.find(query, MongoDTO.class, "testdb");
        System.out.println(map.size());
        System.out.println(map.toString());
		System.out.println("List dao 완료");
		
		return map;
	}
	
	
	
}
