package three.team.movie.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import three.team.movie.dao.Mv_genreDAO;

@Service
public class Mv_genreServiceImpl implements Mv_genreService {

	@Autowired
	private Mv_genreDAO mv_genreDAO;
	
	@Override
	public List<Map<String, String>> getGenreList() {
		List<Map<String, String>> genreList = mv_genreDAO.getGenreList();
		return genreList;
	}

}
