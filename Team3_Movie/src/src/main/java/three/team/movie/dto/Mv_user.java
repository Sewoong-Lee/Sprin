package three.team.movie.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Mv_user {
	private String user_id;
	private String passwd;
	private String user_name;
	private String email;
	private String tel;
	private String file_name; //�프로필 파일명
	private String admin;
	private int zip;
	private String addr1;
	private String addr2;
	private List<String> tag;
}
