package three.team.movie.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public String upload(MultipartFile profile);

	public void getPhoto(String fileName, HttpServletResponse response) throws Exception;
}
