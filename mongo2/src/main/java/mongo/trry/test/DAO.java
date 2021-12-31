package mongo.trry.test;

import java.util.List;
import java.util.Map;

public interface DAO {
	public void insertuserdao(Map<String, Object> userinfo);

	public void removemongo(String key, String value);
	
	public void updatemongo(Map<String, Object> value);
	
	public List<MongoDTO> findmongo(String name);
}
