package three.team.movie.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Mv_movie_data {

	private String genre_code;	
	private int movie_num;	
	private String country_name;	
	private String movie_name;	
	private String director;	
	private String movie_memo;	
	private String movie_poster_link;	
	private String actor_name;	
	private Date reg_date; 	
	private Date modify_date; 	 
	
}


