package three.team.movie.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import three.team.movie.dto.Mv_user;

public interface Mv_userService {
	public void insert(Mv_user mv_user, MultipartFile profile);

	public  Map<String, Object> overlap(String userid);

	public Map<String, String> emailChecked(String email);

	public Map<String, Object> login(String user_id, String passwd);

	public Mv_user selectOne(String user_id);

	public void delete(String user_id);

	public void update(List<String> newTag, Map<String, Object> param, MultipartFile profile);

	public void updatePw(String user_id, String newPw);


}
