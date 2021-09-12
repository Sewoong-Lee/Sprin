package three.team.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import three.team.movie.dao.Mv_userDAO;
import three.team.movie.dto.Mv_user;

@Service
public class Mv_userServiceImpl implements Mv_userService{

	@Autowired
	private Mv_userDAO mv_userDAO;
	@Autowired
	private FileService fileService;
	@Autowired
	private User_tagService user_tagService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder ;
	
	@Override
	public void insert(Mv_user mv_user, MultipartFile profile) {
		//아이디 중복 체크에서 사용 가능해야만 이 쪽으로 넘어오게 만들면
		//굳이 selectOne 할 필요 없음!!!
		
		//파일과 태그 분리
		
		//파일 업로드 및 파일명 가져오기.
		System.out.println("profile : " + profile);
		String fileNameSaved = fileService.upload(profile);
		mv_user.setFile_name(fileNameSaved);
		System.out.println(mv_user);
		
		//비밀번호 암호화
		String bCryptPw = bCryptPasswordEncoder.encode(mv_user.getPasswd());
		mv_user.setPasswd(bCryptPw);
		
		mv_userDAO.insert(mv_user);
		
		//태그 저장
		List<String> tagSelected = mv_user.getTag();
		if (tagSelected == null) {
			return ;
		}
		String user_id=mv_user.getUser_id();
		user_tagService.tagSelected(tagSelected, user_id);
		
	}

	@Override
	public Map<String, Object> overlap(String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		Mv_user user = mv_userDAO.selectOne(userid);
		int code = 0;
		String resultMsg = "";
		if (user == null) {
			code = 0;
			resultMsg = "사용 가능한 아이디입니다.";
		}
		else {
			code = 1;
			resultMsg = "이미 사용 중인 아이디입니다.\n다른 아이디를 입력해주세요.";
		}
		resultMap.put("code", code);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, String> emailChecked(String email) {
		return mv_userDAO.emailChecked(email);
	}

	@Override
	public Map<String, Object> login(String user_id, String passwd) {
		Mv_user user =  mv_userDAO.selectOne(user_id);
		System.out.println(user);
		Map<String, Object> loginResult = new HashMap<>();
		int code ;
		String msg= "";
		if (user == null) {
			code = -1;
			msg = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
		}
		else {
			if (bCryptPasswordEncoder.matches(passwd, user.getPasswd())){
				code = 1;
				msg = "로그인 성공";
			}
			else {
				code = 0;
				msg = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
			}
		}
		loginResult.put("code", code);
		loginResult.put("msg", msg);
		return loginResult;
	}

	@Override
	public Mv_user selectOne(String user_id) {
		return mv_userDAO.selectOne(user_id);
	}

	@Override
	public void delete(String user_id) {
		mv_userDAO.delete(user_id);
	}

	@Override
	public void update(List<String> newTag, Map<String, Object> param, MultipartFile profile) {
		//프로필 사진, 태그, 폰번, 우편번호, 주소, 상세주소
		Map<String, Object> newInfoMap = new HashMap<>();
		String user_id = (String) param.get("user_id");
		String tel = (String) param.get("tel");
		int zip = Integer.parseInt((String)param.get("zip"));
		String addr1 = (String) param.get("addr1");
		String addr2 = (String) param.get("addr2");
		String fileNameSaved = null;
		if (profile != null) {
			fileNameSaved = fileService.upload(profile);
			newInfoMap.put("file_name",fileNameSaved);
			//기존 파일 삭제
		}
		newInfoMap.put("user_id", user_id);
		newInfoMap.put("tel", tel);
		newInfoMap.put("zip", zip);
		newInfoMap.put("addr1", addr1);
		newInfoMap.put("addr2", addr2);
		mv_userDAO.update(newInfoMap);
		
		//태그 저장
		if (newTag == null) {
			user_tagService.delete(user_id);
		}
		else {
			user_tagService.delete(user_id);
			user_tagService.tagSelected(newTag, user_id);
		}
	}

	@Override
	public void updatePw(String user_id, String newPw) {
		Map<String, String> newPwMap = new HashMap<>();
		String bCryptPw = bCryptPasswordEncoder.encode(newPw);
		newPwMap.put("user_id", user_id);
		newPwMap.put("bCryptPw", bCryptPw);
		mv_userDAO.updatePw(newPwMap);
	}

}
