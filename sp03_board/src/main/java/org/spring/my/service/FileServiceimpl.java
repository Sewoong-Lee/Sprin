package org.spring.my.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.my.controller.MemberController;
import org.spring.my.dao.FileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceimpl implements FileService{
	
	private static final Logger logger = LoggerFactory.getLogger(FileServiceimpl.class);
	
	//루트컨텍스트xml에서 빈 생성하고 자동 주입
	//파일 업로드 디렉토리 생성
	@Autowired
	private String uploadDir;
	
	@Autowired
	private FileDAO filedao;
	
	//파일을 폴더에 저장 하고 파일명을 리턴하는 메소드
	@Override
	public String fileupload(MultipartFile file) {
		//실제 파일이름
		String originfilename = file.getOriginalFilename();
		logger.info(originfilename);
		
		//파일네임 공백처리
		if(originfilename == "") return ""; //파일이 없다면 리턴
		
		//파일 이름이 겹치지 않도록 파일 이름 생성
		//천분의 1초의 시간을 파일 이름에 합쳐 중복이 안생기게 만듬
		String filename= System.currentTimeMillis() + originfilename;
		
		//파일 저장위치 설정
		File f= new File(uploadDir, filename);
		 
		try {
			file.transferTo(f);  //파일 폴더에 저장
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filename;
	}

	@Override
	public void insertboardfile(Map<String, Object> fmap) {
		filedao.insertboardfile(fmap);
	}

	
	
	
	
	
	
	
	
}
