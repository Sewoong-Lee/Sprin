package mongo.trry.test;

import java.util.List;
import java.util.Map;

public interface UserModifyService {
	
	public void insertuserinfo(Map<String, Object> info);
	public void removemongo(String key,String value);
	public void updatemongo(Map<String, Object> map);
	public List<MongoDTO> findmongo(String name);
}
