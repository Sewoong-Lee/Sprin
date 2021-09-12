package three.team.movie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import three.team.movie.dao.User_tagDAO;

@Service
public class User_tagServiceImpl implements User_tagService {

	@Autowired
	private User_tagDAO user_tagDAO;
	
	
	@Override
	public void tagSelected(List<String> tagSelected, String user_id) {
		for(int i = 0 ; i <tagSelected.size() ; i ++) {
			Map<String, String> tagMap = new HashMap<>();
			tagMap.put("user_id", user_id);
			tagMap.put("tag", tagSelected.get(i));
			user_tagDAO.tagSelected(tagMap);
		}
	}
	@Override
	public List<Map<String, String>> getUserTag(String user_id) {
		return user_tagDAO.getUserTag(user_id);
	}
	@Override
	public void tagUpdate(List<String> newTag, String user_id) {
		for(int i = 0 ; i <newTag.size() ; i ++) {
			Map<String, String> tagMap = new HashMap<>();
			tagMap.put("user_id", user_id);
			tagMap.put("tag", newTag.get(i));
			user_tagDAO.tagUpdate(tagMap);
		}
	}
	@Override
	public void delete(String user_id) {
		user_tagDAO.delete(user_id);
	}


}
