package three.team.movie.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import three.team.movie.dao.CinemaDAO;
import three.team.movie.dto.Cinema;
import three.team.movie.dto.Mv_city;
import three.team.movie.dto.Mv_sales;
import three.team.movie.dto.Mv_seats;
import three.team.movie.dto.Mv_time;

@Service
public class CinemaServiceimpl implements CinemaService{
	
	@Autowired
	private CinemaDAO cinemaDAO;
	
	@Override
	public List<Mv_city> cityselectlist() {
		
		return cinemaDAO.cityselectlist();
	}

	@Override
	public List<Cinema> cinemaselectlist(String city_code) {
		
		return cinemaDAO.cinemaselectlist(city_code);
	}

	@Override
	public List<Mv_time> timeselectlist(int movie_num) {
		
		return cinemaDAO.timeselectlist(movie_num);
	}

	@Override
	public List<Mv_time> saletimelist(Map<String, Object> timemap) {
		return cinemaDAO.saletimelist(timemap);
	}

	@Override
	public List<Mv_seats> seats_alllist(String time_code) {
		return cinemaDAO.seats_alllist(time_code);
	}
	
	@Transactional
	@Override
	public void salesinsert(Mv_sales mv_sales) {
		cinemaDAO.salesinsert(mv_sales);
		
		System.out.println("인서트 후:"+mv_sales);
		
		Map<String,Object> seatsmap = new HashMap<>();
		seatsmap.put("sal_num", mv_sales.getSal_num());
		seatsmap.put("seats_code", mv_sales.getSeats_code());
		cinemaDAO.sales_seatsinsert(seatsmap);
		
		
	}

	@Override
	public int slectmaxsal_num() {
		// TODO Auto-generated method stub
		return cinemaDAO.slectmaxsal_num();
	}

	
}
