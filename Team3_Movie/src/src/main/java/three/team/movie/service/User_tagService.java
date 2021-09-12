package three.team.movie.service;

import java.util.List;
import java.util.Map;

public interface User_tagService {
	public void tagSelected(List<String> tagSelected, String user_id);

	public List<Map<String, String>> getUserTag(String user_id);

	public void tagUpdate(List<String> newTag, String user_id);

	public void delete(String user_id);

}
