package three.team.movie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import three.team.movie.dto.Cinema;
import three.team.movie.dto.Mv_city;
import three.team.movie.dto.Mv_sales;
import three.team.movie.dto.Mv_seats;
import three.team.movie.dto.Mv_time;

@Repository
public class CinemaDAOimpl implements CinemaDAO {
	
	@Autowired
	private SqlSession SqlSession;
	
	@Autowired
	public List<Mv_city> cityselectlist() {
		
		return SqlSession.selectList("three.team.movie.CinemaMapper.cityselectlist");
	}

	@Override
	public List<Cinema> cinemaselectlist(String city_code) {
		return SqlSession.selectList("three.team.movie.CinemaMapper.cinemaselectlist", city_code);
	}

	@Override
	public List<Mv_time> timeselectlist(int movie_num) {
		return SqlSession.selectList("three.team.movie.CinemaMapper.timeselectlist", movie_num);
	}

	@Override
	public List<Mv_time> saletimelist(Map<String, Object> timemap) {
		
		return SqlSession.selectList("three.team.movie.CinemaMapper.saletimelist",timemap);
	}

	@Override
	public List<Mv_seats> seats_alllist(String time_code) {
		return SqlSession.selectList("three.team.movie.CinemaMapper.seats_alllist",time_code);
	}

	@Override
	public void salesinsert(Mv_sales mv_sales) {
		SqlSession.insert("three.team.movie.CinemaMapper.salesinsert",mv_sales);
	}

	@Override
	public void sales_seatsinsert(Map<String,Object> seatsmap) {
		SqlSession.insert("three.team.movie.CinemaMapper.sales_seatsinsert",seatsmap);
		
	}

	@Override
	public int slectmaxsal_num() {
		
		return SqlSession.selectOne("three.team.movie.CinemaMapper.slectmaxsal_num");
	}


}
