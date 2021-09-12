package three.team.movie.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import three.team.movie.dao.BoardFileDAO;
import three.team.movie.dto.Mv_board_file;

@Service
public class BoardFileServiceimpl implements BoardFileService{
	private static final Logger logger = LoggerFactory.getLogger(BoardFileServiceimpl.class);
	
	//루트컨텍스트xml에서 빈 생성하고 자동 주입
	//파일 업로드 디렉토리 생성
	@Autowired
	private String uploadDir;
	
	@Autowired
	private BoardFileDAO boardFileDAO;
	
	
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
	public void insertdoardfilelist(List<MultipartFile> files, int board_num) {
		//매퍼에게 전달할 맵
		Map<String, Object> fmap = new HashMap<>();
		
		//파일맵에 bnum저장
		fmap.put("board_num", board_num);
		
		//파일이름 리스트 만들기
		List<String> filenamelist = new ArrayList<>();
				
		//만약 files가 널이라면(파일 추가 인풋창을 다 날리면) 리턴
		if(files == null) return ;
				
		for(MultipartFile mf :files) {
			//파일 업로드 저장
			String board_file_name = fileupload(mf);
					
			//파일 네임이 공백이 아닐때만 추가해라
			if(!board_file_name.equals("")) {
				filenamelist.add(board_file_name);
			}
		}
		//filenamelist의 사이즈가 0이면 돌아가라
		if(filenamelist.size() == 0) return ;
		fmap.put("filenamelist", filenamelist);
		//System.out.println(fmap);
		boardFileDAO.insertboardfile(fmap);
		
	}

	@Override
	public List<Mv_board_file> select_board_filelist(int board_num) {
		
		return boardFileDAO.select_board_filelist(board_num);
	}

	@Override
	public void board_file_delete(int board_num) {
		boardFileDAO.board_file_delete(board_num);
		
	}

	@Override
	public void board_filr_delete(int board_file_num) {
		boardFileDAO.board_filr_delete(board_file_num);
		
	}
}
