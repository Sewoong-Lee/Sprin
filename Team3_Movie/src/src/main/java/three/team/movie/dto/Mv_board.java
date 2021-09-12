package three.team.movie.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Mv_board {

	private int board_num;	
	private String user_id;
	private String subject;	
	private String content;	
	private int read_cnt;	
	private int like_cnt;	
	private String ip;	
	private Date reg_date;	
	private Date modify_date;	
	
	private List<MultipartFile> files;
	
}
