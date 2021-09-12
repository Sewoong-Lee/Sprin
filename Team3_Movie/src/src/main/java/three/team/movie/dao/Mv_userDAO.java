package three.team.movie.dao;

import java.util.Map;

import three.team.movie.dto.Mv_user;

public interface Mv_userDAO {
	public void insert(Mv_user mv_user);

	public Mv_user selectOne(String userid);

	public void insertNaverLogin(Map<String, String> resultMap);

	public Map<String, String> emailChecked(String email);

	public void delete(String user_id);

	public void update(Map<String, Object> newInfoMap);

	public void updatePw(Map<String, String> newPwMap);

}
