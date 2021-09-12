package three.team.movie.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Mv_movie_reply {

	private int mr_num;	
	private int movie_num;	
	private String user_id;	
	private String content;	
	private int like_cnt;	
	private int dis_like_cnt;	
	private String ip;	
	private double star_rating;	//별평점 추가 
	private int re_step;	
	private int re_level;
	private Date reg_date;	
	private Date modify_date;	
	
}
