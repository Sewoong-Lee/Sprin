package three.team.movie.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Mv_sales {

	private int sal_num;	
	private String time_code;	
	private String user_id;	
	private int tickets;
	private int price;	
	private String sales_check;	
	private Date reg_date;	
	
	private List<String> seats_code;
}
