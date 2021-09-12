package three.team.movie.service;

import java.util.Map;

public interface NaverService {
	public Map<String, String> getApiURL() throws Exception;

	public String getToken(String code, String state) throws Exception;

	public Map<String,String> getUserInfo(String access_token) throws Exception;

	public void insert(Map<String, String> resultMap);
}
