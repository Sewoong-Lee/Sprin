package mongo.trry.test;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserModifyServiceimpl implements UserModifyService{
	@Autowired
	private DAO dao;
	
	@Override
	public void insertuserinfo(Map<String, Object> info){
		System.out.println("서비스 진입");
		dao.insertuserdao(info);
		
	}
	
	@Override
	public void removemongo(String key,String value) {
//		Criteria criteria = new Criteria(key);
//        criteria.is(value);
//		
//		Query query = new Query(criteria);
//        
//		dao.remove(query, "person");
		System.out.println("서비스 진입");
		dao.removemongo(key,value);
        
	}
	
	@Override
	public void updatemongo(Map<String, Object> map) {
		System.out.println("서비스 진입");
		dao.updatemongo(map);
		
	}
	
	@Override
	public List<MongoDTO> findmongo(String name) {
		List<MongoDTO> map = dao.findmongo(name);
		
		return map;
	}

	
}
