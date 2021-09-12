package three.team.movie.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Mv_board_reply {

	private int board_reply_num;	
	private int board_num;	
	private String user_id;	
	private String content;	
	private String ip;	
	private int re_step;	
	private int re_level;	
	private Date reg_date;	
	private Date modify_date;	
	
}
