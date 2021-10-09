package neo.com.kor.sdfac.testmap.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import neo.com.kor.sdfac.testmap.service.mapService;
import neo.com.util.FileUtil;
import neo.com.util.UtilCommon;

@Controller
public class mapController {
	@Resource (name = "mapService")
	private mapService mapservice;
	
	@RequestMapping(value = "/kor/sdfac/testmap/testmap.do")
	public String GoMap(@RequestParam Map<String, Object> commandMap, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		session.setAttribute("nowMenuNo", ""); //메뉴 세션 초기화
		
		
		return "kor/sdfac/testmap/testmap";
	}
	
	@ResponseBody
	@RequestMapping (value = "/kor/sdfac/testmap/mapShrech.do")
	public List<Map<String, Object>> maptest(@RequestParam Map<String, Object> mapmap, Model model, 
			@RequestParam Map<String, Object> commandMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		session.setAttribute("nowMenuNo", ""); //메뉴 세션 초기화
		
		System.out.println("서치 컨트롤러 진입");
		System.out.println(mapmap.toString());
		
		if(Integer.parseInt((String) mapmap.get("checked")) == 1 ) {
			List<Map<String, Object>> checkedlistmap = mapservice.checkedselectSerch(mapmap);
			System.out.println("범위 리스트 값");
			System.out.println(checkedlistmap.toString());
			return checkedlistmap;
		}else {
			List<Map<String, Object>> listmap = mapservice.selectSerch(mapmap);
			System.out.println("리스트 값");
			System.out.println(listmap.toString());

			return listmap;
		}
		

	}
	
	@RequestMapping(value = "/kor/sdfac/testmap/GomapInsert.do")
	public String GoMapInsert(@RequestParam Map<String, Object> commandMap, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("좌표 폼으로");
		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		session.setAttribute("nowMenuNo", ""); //메뉴 세션 초기화
		
		
		return "kor/sdfac/testmap/mapInsert";
	}
		
		
		@RequestMapping(value = "/kor/sdfac/testmap/mapInsert.do", method = RequestMethod.POST)
	public String MapInsert(@RequestParam Map<String, Object> commandMap, Model model,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {
		
		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		
		System.out.println("좌표 등록");
		System.out.println(commandMap.toString());
		
		// 운영시간의 내용을 하나로 만들기
		String ON_DAY = (String) commandMap.get("ON_DAY1")+" "+commandMap.get("ON_DAY2")+" ~ "+commandMap.get("ON_DAY3")+" "+commandMap.get("ON_DAY4");
		System.out.println("ON_DAY " + ON_DAY);
		
		try {
			
			commandMap.put("ON_DAY", ON_DAY);
			
			// 폴더 파일 저장 경로
	  		Properties props = new Properties();
	  		props.load(new java.io.BufferedInputStream( getClass().getClassLoader().getResourceAsStream("config.properties")) );
	  		String strFilePath = props.getProperty("uploadsDir").trim();
	  		String pathUrl = "/upload";
	  		pathUrl += "/testmap";
	  		strFilePath = strFilePath+"/testmap";
	  		
	  		Map<String, Map<String, String>> fileInfoMap = FileUtil.doMultiFileUpLoad(request, strFilePath, pathUrl);
			if (fileInfoMap != null){
				/* int i=1; */
			    Iterator<String> iterator = fileInfoMap.keySet().iterator();
			    while (iterator.hasNext()){
			        Map<String, String> fileMap = new HashMap<String, String>();
			        String key = iterator.next();
			        fileMap = fileInfoMap.get(key);
			        
			        //뭐로 불러오는지 확인
					/*
					 * commandMap.put("c_fieldname", fileMap.get("fieldname").toString());
					 * commandMap.put("c_fname", fileMap.get("filename").toString());
					 * commandMap.put("c_refilename", fileMap.get("filerename").toString());//이거로
					 * commandMap.put("c_filepath", fileMap.get("fileurlpath").toString()); 
					 * 읽어옴 commandMap.put("file_num", i);
					 */
			        
			        // d - sdfac-upload-testmap 에 저장되어있음
			        commandMap.put("filename", fileMap.get("filerename").toString());
			        
			    }
			    System.out.println("commandMap1 "+commandMap.toString());
			    //인서트 서비스
			    mapservice.mapInsert(commandMap);
			}
		} catch (Exception e) {
			model.addAttribute("commandMap", commandMap);
			model.addAttribute("alertMsg", "오류가 발생 하였습니다. 관리자에게 문의 바랍니다.");
			model.addAttribute("gotoUrl", "/kor/sdfac/rent/application_write.do");
			return "cms/common/alert";
		}
	    
		model.addAttribute("commandMap", commandMap);
		model.addAttribute("inMsg", "정상 등록 완료");
		
		//return "redirect:/common/kor/sdfac/Confirm.do";
		System.out.println("commandMap2 "+commandMap.toString());
		return "kor/sdfac/testmap/mapInsert";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * public String maptest (@RequestParam Map<String, Object> mapmap, Model model,
	 * 
	 * @RequestParam Map<String, Object> commandMap, HttpServletRequest request,
	 * HttpServletResponse response) throws Exception {
	 * 
	 * // 보안필터링 추가 commandMap = UtilCommon.checkMapData(commandMap, ""); HttpSession
	 * session = request.getSession(true); session.setAttribute("nowMenuNo", "");
	 * //메뉴 세션 초기화
	 * 
	 * 
	 * mapmap.put("cityName", "성동구"); System.out.println(mapmap);
	 * 
	 * ArrayList<Map<String, Object>> map = mapservice.selectSerch(mapmap);
	 * 
	 * model.addAttribute("SerchMap", map);
	 * 
	 * return "kor/sdfac/testmap/testmap"; }
	 */
	
}
