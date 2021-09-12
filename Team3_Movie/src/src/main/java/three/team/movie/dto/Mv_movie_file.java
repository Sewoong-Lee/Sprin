package three.team.movie.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Mv_movie_file {

	private int mf_num;
	private int movie_num;	
	private String mf_file_name;	
	private Date reg_date;	

}
