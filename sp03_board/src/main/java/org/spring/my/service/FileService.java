package org.spring.my.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	//파일을 폴더에 저장 하고 파일명을 리턴하는
	public String fileupload(MultipartFile file);

	public void insertboardfile(Map<String, Object> fmap);
}
