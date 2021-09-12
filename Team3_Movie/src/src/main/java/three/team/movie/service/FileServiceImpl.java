package three.team.movie.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private String uploadDir;
	
	@Override
	public String upload(MultipartFile profile) {
		String originalFileName = profile.getOriginalFilename();
		if (originalFileName == "") {
			return "";
		}
		String fileNameSaved = System.currentTimeMillis()+originalFileName;
		
		File f = new File(uploadDir, fileNameSaved);
		try {
			profile.transferTo(f);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileNameSaved;
	}

	@Override
	public void getPhoto(String fileName, HttpServletResponse response) throws Exception {
		String fileUrl = uploadDir + "/" + fileName;
		//파일 읽기 스트림 생성
		FileInputStream fis = new FileInputStream(fileUrl);
		
		//파일 이름 인코딩
		fileName = URLEncoder.encode(fileName, "UTF-8");
		//파일 보내기 스트림 생성
		OutputStream out = response.getOutputStream();
		//fis 읽어서 out으로 보내기
		//FileCopyUtils.copy(어디서에서 읽어서, 어디로 보낼 것인가);
		FileCopyUtils.copy(fis, out);
	}

}
