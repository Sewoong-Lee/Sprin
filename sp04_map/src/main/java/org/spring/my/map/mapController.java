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

import neo.com.kor.sdfac.testmap.service.SdfacmapService;
import neo.com.neomanager.login.service.AdminInfo;
import neo.com.util.FileUtil;
import neo.com.util.UtilCommon;

@Controller
public class SdfacmapController {
	@Resource (name = "mapService")
	private SdfacmapService mapservice;
	
	//고객 지도 화면 이동
	@RequestMapping(value = "/kor/sdfac/testmap/testmap.do")
	public String GoMap(@RequestParam Map<String, Object> commandMap, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화
		
		String user_id = (String) session.getAttribute("user_id");
		
		AdminInfo aInfo = (AdminInfo) session.getAttribute( "adminInfo" );
		
		List<Map<String, Object>> jlist = mapservice.JosoList();
		List<Map<String, Object>> clist = mapservice.AllCategories();
		model.addAttribute("clist", clist);
		model.addAttribute("jlist", jlist);

		return "kor/sdfac/testmap/testmap";
	}
	
	//고객이 검색하기 버튼을 눌렀을떄
	@ResponseBody
	@RequestMapping(value = "/kor/sdfac/testmap/mapShrech.do")
	public List<Map<String, Object>> maptest(@RequestParam Map<String, Object> mapmap, Model model,
			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화

//		System.out.println("서치 컨트롤러 진입");
//		System.out.println(mapmap.toString());
//		System.out.println("mapShrech : " + mapmap.toString());
		if (Integer.parseInt((String) mapmap.get("checked")) == 1) {
			List<Map<String, Object>> checkedlistmap = mapservice.checkedselectSerch(mapmap);
//			System.out.println("범위 리스트 값");
//			System.out.println(checkedlistmap.toString());
			return checkedlistmap;
		} else {
			List<Map<String, Object>> listmap = mapservice.selectSerch(mapmap);
//			System.out.println("리스트 값");
//			System.out.println(listmap.toString());

			return listmap;
		}

	}

	// 고객 신규 문화공간 등록 신청을 클릭 하면
	@RequestMapping(value = "/kor/sdfac/testmap/GomapInsert.do")
	public String GoMapInsert(@RequestParam Map<String, Object> commandMap, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		System.out.println("등록 제안 인서트 폼으로");
		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화

		Object user_id = session.getAttribute("user_id");
//		System.out.println(session.getAttribute("user_id"));
//		System.out.println(session.getAttribute("m_name"));
		
		List<Map<String, Object>> clist = mapservice.GoInsert();
		List<Map<String, Object>> slist = mapservice.OrderInsertForm_Service();
		List<Map<String, Object>> jlist = mapservice.JosoList();
		model.addAttribute("clist", clist);
		model.addAttribute("slist", slist);
		model.addAttribute("jlist", jlist);
		model.addAttribute("user_id", user_id);

		return "kor/sdfac/testmap/mapOrderInsert";
	}
		
	// 고객이 신규 등록 신청 버튼을 클릭하면 인서트
	@RequestMapping(value = "/kor/sdfac/testmap/OrderInsert.do", method = RequestMethod.POST)
	public String MapInsert(@RequestParam Map<String, Object> commandMap, 
	@RequestParam(value="s_idxs", required=false) List<Integer> s_idxs, Model model,
	HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {
		
		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		
		
//		System.out.println("좌표 등록");
//		System.out.println(commandMap.toString());
		
		//String on_day_week = (String) commandMap.get("week_on_day1")+"~"+commandMap.get("week_on_day2");
		//String on_day_weekend = (String) commandMap.get("weekend_on_day1")+"~"+commandMap.get("weekend_on_day2");
		//System.out.println("on_day_Week " + on_day_week);
		//System.out.println("on_day_Weekend " + on_day_weekend);
		//commandMap.put("on_day_week", on_day_week);
		//commandMap.put("on_day_weekend", on_day_weekend);
		
		try {
			String user_id = (String) session.getAttribute("user_id");
			String m_name = (String) session.getAttribute("m_name");
			commandMap.put("m_email", user_id);
			commandMap.put("m_name2", m_name);
			
			
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
					
					commandMap.put("filedname", fileMap.get("fieldname").toString());
					commandMap.put("filetype", fileMap.get("filetype").toString()); 
					commandMap.put("filename", fileMap.get("filename").toString());
					commandMap.put("filepath", fileMap.get("filepath").toString()); 
					commandMap.put("fileurlpath", fileMap.get("fileurlpath").toString()); 
					commandMap.put("filesize", fileMap.get("filesize").toString()); 
					commandMap.put("filerename", fileMap.get("filerename").toString());//이거로
					//commandMap.put("file_num", i);
					 
			        
			        // d - sdfac-upload-testmap 에 저장되어있음
			        
			    }
			    commandMap.put("gubun", 0);
				
//			    System.out.println("commandMap1 "+commandMap.toString());
			    mapservice.mapOrderInsert_con(commandMap);
			    
			    //방금 인서트한 mn_idx 구하기
			    Map<String, Object> mn_idx_map = mapservice.OrderInsert_Max_mnidx();
//			    System.out.println("mn_idx : "+mn_idx_map.get(""));
			    int mn_idx = (int) mn_idx_map.get("");
			    
				//s_idx 인서트
			    if(s_idxs != null) {
			    	for (int s_idx : s_idxs) { 
//						System.out.println("s_idx : "+s_idx);
						Map<String, Object> checkedserviceMap = new HashMap<String, Object>();
						checkedserviceMap.put("s_idx", s_idx);
						checkedserviceMap.put("mn_idx", mn_idx);
//						System.out.println(checkedserviceMap.toString());
						mapservice.checked_service_new_insert(checkedserviceMap);
					}
			    }
				
				
			}
		} catch (Exception e) {
			model.addAttribute("commandMap", commandMap);
			model.addAttribute("alertMsg", "오류가 발생 하였습니다. 관리자에게 문의 바랍니다.");
			model.addAttribute("gotoUrl", "/kor/sdfac/rent/application_write.do");
			return "cms/common/alert";
		}
		model.addAttribute("commandMap", commandMap);
		
		redirectAttributes.addFlashAttribute("Confirm", "success");
		redirectAttributes.addFlashAttribute("Confirm_alert", "정상적으로 등록 되었습니다.");
		redirectAttributes.addFlashAttribute("Confirm_url", "/kor/sdfac/rent/application_result.do");
		
		//return "redirect:/common/kor/sdfac/Confirm.do";
//		System.out.println("commandMap2 "+commandMap.toString());
		return "redirect:/kor/sdfac/testmap/testmap.do";
	}
	
	
	//고객이 대분류 셀렉트 박스를 변경하면
	@ResponseBody
	@RequestMapping(value = "/kor/sdfac/testmap/m_categories_Serch.do")
	public List<Map<String, Object>> MCategoriesSerch(@RequestParam Map<String, Object> mapmap, Model model,
			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화

//		System.out.println("대분류 셀렉트 변경시 맵 : " + mapmap.toString());

		List<Map<String, Object>> listmap = mapservice.MCategoriesSerch(mapmap);

//		System.out.println(listmap.toString());
		return listmap;
	}
	
	//고객이 중분류 셀렉트박스를 변경하면
	@ResponseBody
	@RequestMapping(value = "/kor/sdfac/testmap/m_categories_Change.do")
	public List<Map<String, Object>> m_categories_Change(@RequestParam Map<String, Object> mapmap, Model model,
			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화

//		System.out.println(mapmap.toString());

		List<Map<String, Object>> listmap = mapservice.m_categories_Change(mapmap);

//		System.out.println(listmap.toString());
		return listmap;
	}
	
	//고객이 주소 셀렉트박스를 변경하면
	@ResponseBody
	@RequestMapping(value = "/kor/sdfac/testmap/select_joso_Change.do")
	public List<Map<String, Object>> select_joso_Change(@RequestParam Map<String, Object> mapmap, Model model,
			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화

//		System.out.println(mapmap.toString());

		List<Map<String, Object>> listmap = mapservice.select_joso_Change(mapmap);

//		System.out.println(listmap.toString());
		return listmap;
	}
				
			
	//분류 박스의 버튼을 누르면
	@ResponseBody
	@RequestMapping(value = "/kor/sdfac/testmap/Categories_Click.do")
	public List<Map<String, Object>> s_categories_serch(@RequestParam Map<String, Object> mapmap, Model model,
			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화

//		System.out.println("Categories_Click" + mapmap.toString());

		List<Map<String, Object>> callist = mapservice.Categories_Click(mapmap);
		model.addAttribute("callist", callist);

//		System.out.println("소분류 클릭 리스트 :" + callist);

		return callist;
	}
		
//
//	
//	// 관리자 제안 리스트로
//	@RequestMapping(value = "/kor/sdfac/testmap/mapOrderList.do")
//	public String maplist(@RequestParam Map<String, Object> mapmap, Model model,
//			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		// 보안필터링 추가
//		commandMap = UtilCommon.checkMapData(commandMap, "");
//	    HttpSession session = request.getSession();
//	    AdminInfo aInfo = (AdminInfo) session.getAttribute( "adminInfo" );
//		
//		/* commandMap.put("branchId", aInfo.getBranchId()); */
//	    
//		//페이징처리
//		Map<String, Integer> page = new HashMap<String, Integer>();
//		int curpage = 1; //현재 페이지
//		int perpage = 10; //한페이지당 게시물수
//		int perblock = 10; //한 화면 페이지의 블럭의 수
//		
//		//페이징처리(계산 예정)
//		int totpage = 0; //전체 페이지수
//		int startnum = 0; //시작번호
//		int endnum = 0; //끝번호
//		int startpage = 0; //블럭의 시작 페이지 
//		int endpage = 0; // 블럭의 끝 페이지
//		
//		//전체 게시물 수 구하기
//		int totcnt = mapservice.Map_Order_TotCnt();
//		
//		//전체 페이지 수 구하기
//		totpage = totcnt / perpage;
//		if(totcnt%perpage != 0) { //나머지가 있으면 1페이지 추가
//			totpage += 1;
//		}
//		page.put("totpage", totpage);
//		
//		//현재 페이지
//		if(commandMap.get("curpage") != null) {
//			curpage = Integer.parseInt((String) commandMap.get("curpage"));
//		}
//		page.put("curpage", curpage);
//		//시작번호
//		startnum = (curpage-1) * perpage + 1;
//		page.put("startnum", startnum);
//		commandMap.put("startnum", startnum);
//		//끝번호
//		endnum = startnum + perpage -1;
//		page.put("endnum", endnum);
//		commandMap.put("endnum", endnum);
//		
//		//시작페이지
//		startpage = curpage - ((curpage-1) % perblock);
//		page.put("startpage", startpage);
//		//끝페이지
//		endpage = startpage + perblock -1;
//		if(endpage > totpage) endpage = totpage;
//		page.put("endpage", endpage);
//		//페이징처리 끝
//		
//		List<Map<String, Object>> orderlist = mapservice.AllOrderList(commandMap);
//		model.addAttribute("orderlist", orderlist);
//		model.addAttribute("page", page);
//		System.out.println("orderlist " + orderlist.toString());
//
//		return "kor/sdfac/testmap/mapOrderList";
//	}
//	
//	//관리자 컨텐츠 전체 리스트 폼으로
//	@RequestMapping(value = "/kor/sdfac/testmap/mapAllList.do")
//	public String mapAllList(Model model, @RequestParam(value = "s_idxs", required = false) List<Integer> s_idxslist,
//			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		// 보안필터링 추가
//		commandMap = UtilCommon.checkMapData(commandMap, "");
//		HttpSession session = request.getSession(true);
//		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화
//		
//		System.out.println("commandMap : "+commandMap.toString());
//		//System.out.println("s_idxslist : "+s_idxslist.toString());
//		
//		Map<String, Integer> page = new HashMap<String, Integer>();
//		//페이징처리
//		int curpage = 1; //현재 페이지
//		int perpage = 10; //한페이지당 게시물수
//		int perblock = 10; //한 화면 페이지의 블럭의 수
//		
//		//페이징처리(계산 예정)
//		int totpage; //전체 페이지수
//		int startnum; //시작번호
//		int endnum; //끝번호
//		int startpage; //블럭의 시작 페이지 
//		int endpage; // 블럭의 끝 페이지
//		
//		if (commandMap.get("curpage") != null)
//			curpage = Integer.parseInt((commandMap.get("curpage").toString().equals("") ? "1" : commandMap.get("curpage").toString()));
//		
//		if(s_idxslist != null) {
//			commandMap.put("s_idxslist", s_idxslist);
//			commandMap.put("s_idxcount", s_idxslist.size());
//		}
//		
//		//전체 게시물 수 구하기
//		int totcnt = 0;
//		if (commandMap != null && !commandMap.isEmpty()) {
//			System.out.println("commandMap은 널이아니야!!");
//			System.out.println(commandMap.toString());
//			totcnt = mapservice.Map_Serch_TotCnt(commandMap);
//		} else {
//			System.out.println("commandMap은 널이야....");
//			System.out.println(commandMap.toString());
//			totcnt = mapservice.AllList_TotCnt(commandMap);
//		}
//		System.out.println("totcnt "+totcnt);
//		
//		//전체 페이지 수 구하기
//		totpage = totcnt / perpage;
//		if(totcnt%perpage != 0) { //나머지가 있으면 1페이지 추가
//			totpage += 1;
//		}
//		page.put("totpage", totpage);
//		
//		//현재 페이지
//		if(commandMap.get("curpage") != null) {
//			curpage = Integer.parseInt((String) commandMap.get("curpage"));
//		}
//		page.put("curpage", curpage);
//		//시작번호
//		startnum = (curpage-1) * perpage + 1;
//		page.put("startnum", startnum);
//		commandMap.put("startnum", startnum);
//		//끝번호
//		endnum = startnum + perpage -1;
//		page.put("endnum", endnum);
//		commandMap.put("endnum", endnum);
//		
//		//시작페이지
//		startpage = curpage - ((curpage-1) % perblock);
//		page.put("startpage", startpage);
//		//끝페이지
//		endpage = startpage + perblock -1;
//		if(endpage > totpage) endpage = totpage;
//		page.put("endpage", endpage);
//		
//		//return boardDAO.selectlist(mv_board_page);
//		
//		commandMap.put("s_idxslist", s_idxslist);
//		System.out.println("mapAllList commandMap : " + commandMap.toString());
//		// 전체 맵 컨텐츠 셀렉트
//		List<Map<String, Object>> alllist = new ArrayList<Map<String, Object>>();
//		//if (commandMap != null && !commandMap.isEmpty()) {
//		if (commandMap.get("user_id") != null) {
//			System.out.println("commandMap은 널이아니야!!");
//			alllist = mapservice.Map_Contents_Serch(commandMap);
//			
//		} else {
//			System.out.println("commandMap은 널이야....");
//			alllist = mapservice.AllList(commandMap);
//		}
//		
//		System.out.println("alllist : "+alllist.toString());
//		
//		// 전체 카테고리 셀렉트
//		List<Map<String, Object>> clist = mapservice.GoInsert();
//		// 전체 지역 셀렉트
//		List<Map<String, Object>> jlist = mapservice.JosoList();
//		// 전체 서비스 셀렉트
//		List<Map<String, Object>> slist = mapservice.OrderInsertForm_Service();
//
//		model.addAttribute("alllist", alllist);
//		model.addAttribute("clist", clist);
//		model.addAttribute("jlist", jlist);
//		model.addAttribute("slist", slist);
//		model.addAttribute("page", page);
//		model.addAttribute("commandMap", commandMap);
//		model.addAttribute("s_idxslist", s_idxslist);
//		
//		return "kor/sdfac/testmap/mapAllList";
//	}
	
	
	// 고객이 더보기 누르면
	@ResponseBody
	@RequestMapping(value = "/kor/sdfac/testmap/dubogi.do")
	public List<Map<String, Object>> dubogi(@RequestParam Map<String, Object> mapmap, Model model,
			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화

		// 호출한 맵 컨텐츠 데이터 불러오기
		List<Map<String, Object>> callist = mapservice.dubogi(mapmap);
		model.addAttribute("dubogi", callist);

		return callist;
	}
	
//	//관리자 등록 제안 승인 페이지 이동
//	@RequestMapping(value = "/kor/sdfac/testmap/mapOrderInsertDetail.do")
//	public String mapOrderInsertDetail(@RequestParam int mn_idx, Model model,
//			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		// 보안필터링 추가
//		commandMap = UtilCommon.checkMapData(commandMap, "");
//		HttpSession session = request.getSession(true);
//		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화
//
//		System.out.println("mn_idx " + mn_idx);
//		
//		// 전체 지역 셀렉트
//		List<Map<String, Object>> jlist = mapservice.JosoList();
//		// 전체 카테고리 셀렉트
//		List<Map<String, Object>> clist = mapservice.GoInsert();
//		// 서비스 체크값
//		List<Map<String, Object>> mnslist = mapservice.mapOrderInsertChecked_Sercice(mn_idx);
//		System.out.println("mnslist : " + mnslist.toString());
//		// 전체 서비스 리스트
//		List<Map<String, Object>> slist = mapservice.OrderInsertForm_Service();
//		// 제안 컨텐츠 값
//		Map<String, Object> mnlist = mapservice.mapOrderInsertDetail(mn_idx);
//		
//		model.addAttribute("jlist", jlist);
//		model.addAttribute("clist", clist);
//		model.addAttribute("mnlist", mnlist);
//		model.addAttribute("mnslist", mnslist);
//		model.addAttribute("slist", slist);
//
//		return "kor/sdfac/testmap/mapOrderInsertDetail";
//	}
	
	
//	//관리자 등록 승인해서 인서트 실행
//	@RequestMapping(value = "/kor/sdfac/testmap/OrderInsertOk.do", method = RequestMethod.POST)
//	public String OrderInsertOk(@RequestParam(value = "s_idxs", required = false) List<Integer> s_idxs,
//			@RequestParam int mn_idx, Model model, @RequestParam Map<String, Object> commandMap,
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// 보안필터링 추가
//		commandMap = UtilCommon.checkMapData(commandMap, "");
//		HttpSession session = request.getSession(true);
//		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화
//		
//		System.out.println("commandMap : " + commandMap);
//		
//		String user_id = (String) session.getAttribute("user_id");
//		String m_name = (String) session.getAttribute("m_name");
//		commandMap.put("m_email", user_id);
//		commandMap.put("m_name2", m_name);
//		
//		// 요일 문자열 합치기
//		//String on_day_week = (String) commandMap.get("week_on_day1") + "~" + commandMap.get("week_on_day2");
//		//String on_day_weekend = (String) commandMap.get("weekend_on_day1") + "~" + commandMap.get("weekend_on_day2");
//		//System.out.println("on_day_Week " + on_day_week);
//		//System.out.println("on_day_Weekend " + on_day_weekend);
//		//commandMap.put("on_day_week", on_day_week);
//		//commandMap.put("on_day_weekend", on_day_weekend);
//		
//		if(commandMap.get("filecheck") != null){
//			try {
//				// 폴더 파일 저장 경로
//		  		Properties props = new Properties();
//		  		props.load(new java.io.BufferedInputStream( getClass().getClassLoader().getResourceAsStream("config.properties")) );
//		  		String strFilePath = props.getProperty("uploadsDir").trim();
//		  		String pathUrl = "/upload";
//		  		pathUrl += "/testmap";
//		  		strFilePath = strFilePath+"/testmap";
//		  		
//		  		Map<String, Map<String, String>> fileInfoMap = FileUtil.doMultiFileUpLoad(request, strFilePath, pathUrl);
//				if (fileInfoMap != null){
//					/* int i=1; */
//				    Iterator<String> iterator = fileInfoMap.keySet().iterator();
//				    while (iterator.hasNext()){
//				        Map<String, String> fileMap = new HashMap<String, String>();
//				        String key = iterator.next();
//				        fileMap = fileInfoMap.get(key);
//				        
//				        //뭐로 불러오는지 확인
//						
//						commandMap.put("filedname", fileMap.get("fieldname").toString());
//						commandMap.put("filetype", fileMap.get("filetype").toString()); 
//						commandMap.put("filename", fileMap.get("filename").toString());
//						commandMap.put("filepath", fileMap.get("filepath").toString()); 
//						commandMap.put("fileurlpath", fileMap.get("fileurlpath").toString()); 
//						commandMap.put("filesize", fileMap.get("filesize").toString()); 
//						commandMap.put("filerename", fileMap.get("filerename").toString());//이거로
//						//commandMap.put("file_num", i);
//						 
//				        
//				    }
//					
//				    System.out.println("commandMap1 "+commandMap.toString());	
//					
//				}
//			} catch (Exception e) {
//				model.addAttribute("commandMap", commandMap);
//				model.addAttribute("alertMsg", "오류가 발생 하였습니다. 관리자에게 문의 바랍니다.");
//				model.addAttribute("gotoUrl", "/kor/sdfac/rent/application_write.do");
//				return "cms/common/alert";
//			}
//			
//		}
//		
//		// 등록 제안의 내용 본 내용으로 등록
//		mapservice.mapOrderInsertOk(commandMap);
//		
//		int m_idx = mapservice.Max_Midx();
//		System.out.println(m_idx);
//
//		// s_idx 컨텐츠에 인서트
//		if (s_idxs != null) {
//			for (int s_idx : s_idxs) {
//				System.out.println("s_idx : " + s_idx);
//				Map<String, Object> checkedserviceMap = new HashMap<String, Object>();
//				checkedserviceMap.put("s_idx", s_idx);
//				checkedserviceMap.put("m_idx", m_idx);
//				System.out.println(checkedserviceMap.toString());
//				mapservice.checked_service_insert(checkedserviceMap);
//			}
//		}
//
//		// 인서트 후 기존 제안 값 삭제
//		mapservice.mapDeleteOrder_New(mn_idx);
//		mapservice.mapDelete_checked_service_new(mn_idx);
//
//		return "redirect:/kor/sdfac/testmap/mapOrderList.do";
//	}
	
	
	//고객이 기존 데이터 수정 제안을 누르면 폼으로 이동
	@RequestMapping(value = "/kor/sdfac/testmap/mapUpdateFrom.do")
	public String mapupdateFrom(@RequestParam int m_idx, Model model, @RequestParam Map<String, Object> commandMap,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);
		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화

//		System.out.println("m_idx " + m_idx);
		// 전체 카테고리 셀렉트
		List<Map<String, Object>> clist = mapservice.GoInsert();
		// 전체 지역 셀렉트
		List<Map<String, Object>> jlist = mapservice.JosoList();
		// 서비스 체크값
		List<Map<String, Object>> mslist = mapservice.Map_Contents_Checked_Sercice(m_idx);
		// 전체 서비스 리스트
		List<Map<String, Object>> slist = mapservice.OrderInsertForm_Service();
		// 기존 컨텐츠
		Map<String, Object> mlist = mapservice.Map_Contents_SelectOne(m_idx);

		// String week = (String) mlist.get("on_day_week");
		// String weekend = (String) mlist.get("on_day_weekend");

		// String week1 = week.substring(0, 5);
		// String week2 = week.substring(6, 11);
		// String weekend1 = weekend.substring(0, 5);
		// String weekend2 = weekend.substring(6, 11);

		// System.out.println("week : "+week1 +" "+week2);
		// System.out.println("weekend : "+weekend1 +" "+weekend2);

		model.addAttribute("clist", clist);
		model.addAttribute("jlist", jlist);
		model.addAttribute("mslist", mslist);
		model.addAttribute("slist", slist);
		model.addAttribute("mlist", mlist);

		return "kor/sdfac/testmap/mapOrderUpdate";
	}
	
//	//이전 업데이트폼 시간 셋팅
//	@ResponseBody
//	@RequestMapping(value = "/kor/sdfac/testmap/UpdateTimeSet.do")
//	public Map<String, Object> UpdateTimeSet(@RequestParam int m_idx, Model model,
//			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//
//		// 보안필터링 추가
//		commandMap = UtilCommon.checkMapData(commandMap, "");
//		HttpSession session = request.getSession(true);
//		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화
//
//		Map<String, Object> tlist = mapservice.UpdateTimeSet(m_idx);
//
//		if (tlist.get("on_day_week") != null) {
//			String week = (String) tlist.get("on_day_week");
//			String week1 = week.substring(0, 5);
//			String week2 = week.substring(6, 11);
//			System.out.println("week : " + week1 + " " + week2);
//			tlist.put("week1", week1);
//			tlist.put("week2", week2);
//		}
//		if (tlist.get("on_day_weekend") != null) {
//			String weekend = (String) tlist.get("on_day_weekend");
//			String weekend1 = weekend.substring(0, 5);
//			String weekend2 = weekend.substring(6, 11);
//			System.out.println("weekend : " + weekend1 + " " + weekend2);
//			tlist.put("weekend1", weekend1);
//			tlist.put("weekend2", weekend2);
//		}
//
//		return tlist;
//	}
	
	
	//고객이 수정 제안 확인을 누르면
	@RequestMapping(value = "/kor/sdfac/testmap/OrderUpdate.do", method = RequestMethod.POST)
	public String OrderUpdate(@RequestParam Map<String, Object> commandMap,
			@RequestParam(value = "s_idxs", required = false) List<Integer> s_idxs, Model model,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws Exception {

		// 보안필터링 추가
		commandMap = UtilCommon.checkMapData(commandMap, "");
		HttpSession session = request.getSession(true);

//		System.out.println("좌표 등록");
//		System.out.println(commandMap.toString());

		//String on_day_week = (String) commandMap.get("week_on_day1") + "~" + commandMap.get("week_on_day2");
		//String on_day_weekend = (String) commandMap.get("weekend_on_day1") + "~" + commandMap.get("weekend_on_day2");
		//System.out.println("on_day_Week " + on_day_week);
		//System.out.println("on_day_Weekend " + on_day_weekend);
		//commandMap.put("on_day_week", on_day_week);
		//commandMap.put("on_day_weekend", on_day_weekend);
		
		try {
			String user_id = (String) session.getAttribute("user_id");
			String m_name = (String) session.getAttribute("m_name");
			commandMap.put("m_email", user_id);
			commandMap.put("m_name2", m_name);

			// 폴더 파일 저장 경로
			Properties props = new Properties();
			props.load(new java.io.BufferedInputStream(
					getClass().getClassLoader().getResourceAsStream("config.properties")));
			String strFilePath = props.getProperty("uploadsDir").trim();
			String pathUrl = "/upload";
			pathUrl += "/testmap";
			strFilePath = strFilePath + "/testmap";

			Map<String, Map<String, String>> fileInfoMap = FileUtil.doMultiFileUpLoad(request, strFilePath, pathUrl);
			if (fileInfoMap != null) {
				/* int i=1; */
				Iterator<String> iterator = fileInfoMap.keySet().iterator();
				while (iterator.hasNext()) {
					Map<String, String> fileMap = new HashMap<String, String>();
					String key = iterator.next();
					fileMap = fileInfoMap.get(key);

					// 뭐로 불러오는지 확인

					commandMap.put("filedname", fileMap.get("fieldname").toString());
					commandMap.put("filetype", fileMap.get("filetype").toString());
					commandMap.put("filename", fileMap.get("filename").toString());
					commandMap.put("filepath", fileMap.get("filepath").toString());
					commandMap.put("fileurlpath", fileMap.get("fileurlpath").toString());
					commandMap.put("filesize", fileMap.get("filesize").toString());
					commandMap.put("filerename", fileMap.get("filerename").toString());// 이거로
					// commandMap.put("file_num", i);

					// d - sdfac-upload-testmap 에 저장되어있음

				}
				commandMap.put("gubun", 1);


//				System.out.println("commandMap1 " + commandMap.toString());
				mapservice.mapOrderUpdate_con(commandMap);

				// 방금 인서트한 mn_idx 구하기
				Map<String, Object> mn_idx_map = mapservice.OrderInsert_Max_mnidx();
//				System.out.println("mn_idx : " + mn_idx_map.get(""));
				int mn_idx = (int) mn_idx_map.get("");

				// s_idx 인서트
				if (s_idxs != null) {
					for (int s_idx : s_idxs) {
//						System.out.println("s_idx : " + s_idx);
						Map<String, Object> checkedserviceMap = new HashMap<String, Object>();
						checkedserviceMap.put("s_idx", s_idx);
						checkedserviceMap.put("mn_idx", mn_idx);
//						System.out.println(checkedserviceMap.toString());
						mapservice.checked_service_new_insert(checkedserviceMap);
					}
				}

			}
		} catch (Exception e) {
			model.addAttribute("commandMap", commandMap);
			model.addAttribute("alertMsg", "오류가 발생 하였습니다. 관리자에게 문의 바랍니다.");
			model.addAttribute("gotoUrl", "/kor/sdfac/rent/application_write.do");
			return "cms/common/alert";
		}
		model.addAttribute("commandMap", commandMap);

		redirectAttributes.addFlashAttribute("Confirm", "success");
		redirectAttributes.addFlashAttribute("Confirm_alert", "정상적으로 등록 되었습니다.");
		redirectAttributes.addFlashAttribute("Confirm_url", "/kor/sdfac/rent/application_result.do");

		// return "redirect:/common/kor/sdfac/Confirm.do";
//		System.out.println("commandMap2 " + commandMap.toString());
		return "redirect:/kor/sdfac/testmap/testmap.do";
	}
	
	
//	//관리자 수정 제안 디테일 페이지 이동
//	@RequestMapping(value = "/kor/sdfac/testmap/mapOrderUpdateDetail.do")
//	public String mapOrderUpdateDetail(@RequestParam int mn_idx, Model model,
//			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		// 보안필터링 추가
//		commandMap = UtilCommon.checkMapData(commandMap, "");
//		HttpSession session = request.getSession(true);
//		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화
//
//		System.out.println("mn_idx " + mn_idx);
//
//		// 전체 카테고리 셀렉트
//		List<Map<String, Object>> clist = mapservice.GoInsert();
//		// 전체 지역 셀렉트
//		List<Map<String, Object>> jlist = mapservice.JosoList();
//		// 전체 서비스 리스트
//		List<Map<String, Object>> slist = mapservice.OrderInsertForm_Service();
//		// 수정 서비스 체크값
//		List<Map<String, Object>> mnslist = mapservice.mapOrderInsertChecked_Sercice(mn_idx);
//		// 제안 컨텐츠 값
//		Map<String, Object> mnlist = mapservice.mapOrderInsertDetail(mn_idx);
//		// 기존 컨텐츠
//		int m_idx = (int) mnlist.get("m_idx");
//		System.out.println("m_idx : " + m_idx);
//		Map<String, Object> mlist = mapservice.Map_Contents_SelectOne(m_idx);
//		// 기존 서비스 체크값
//		List<Map<String, Object>> mslist = mapservice.Map_Contents_Checked_Sercice(m_idx);
//
//		model.addAttribute("clist", clist);
//		model.addAttribute("jlist", jlist);
//		model.addAttribute("slist", slist);
//		model.addAttribute("mnslist", mnslist);
//		model.addAttribute("mnlist", mnlist);
//		model.addAttribute("mlist", mlist);
//		model.addAttribute("mslist", mslist);
//
//		return "/kor/sdfac/testmap/mapOrderUpdateDetail";
//	}
	
	
	
//	// 업데이트 승인 시간 셋팅
//	@ResponseBody
//	@RequestMapping(value = "/kor/sdfac/testmap/UpdateTimeSetChack.do")
//	public List<Map<String, Object>> UpdateTimeSetChack(@RequestParam int m_idx, @RequestParam int mn_idx, Model model,
//			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//
//		// 보안필터링 추가
//		commandMap = UtilCommon.checkMapData(commandMap, "");
//		HttpSession session = request.getSession(true);
//		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화
//		// 기존 값
//		Map<String, Object> tlist = mapservice.UpdateTimeSet(m_idx);
//
//		/*
//		 * if (tlist.get("on_day_week") != null) { String week = (String)
//		 * tlist.get("on_day_week"); String week1 = week.substring(0, 5); String week2 =
//		 * week.substring(6, 11); System.out.println("week : " + week1 + " " + week2);
//		 * tlist.put("week1", week1); tlist.put("week2", week2); } if
//		 * (tlist.get("on_day_weekend") != null) { String weekend = (String)
//		 * tlist.get("on_day_weekend"); String weekend1 = weekend.substring(0, 5);
//		 * String weekend2 = weekend.substring(6, 11); System.out.println("weekend : " +
//		 * weekend1 + " " + weekend2); tlist.put("weekend1", weekend1);
//		 * tlist.put("weekend2", weekend2); }
//		 */
//
//		// 제안 컨텐츠 값
//		Map<String, Object> mnlist = mapservice.mapOrderInsertDetail(mn_idx);
//		/*
//		 * if (mnlist.get("on_day_week") != null) { String week = (String)
//		 * mnlist.get("on_day_week"); String week1 = week.substring(0, 5); String week2
//		 * = week.substring(6, 11); mnlist.put("week1", week1); mnlist.put("week2",
//		 * week2); } if (mnlist.get("on_day_weekend") != null) { String weekend =
//		 * (String) mnlist.get("on_day_weekend"); String weekend1 = weekend.substring(0,
//		 * 5); String weekend2 = weekend.substring(6, 11); mnlist.put("weekend1",
//		 * weekend1); mnlist.put("weekend2", weekend2); }
//		 */
//
//		List<Map<String, Object>> alist = new ArrayList<Map<String, Object>>();
//		alist.add(tlist);
//		alist.add(mnlist);
//
//		return alist;
//	}
	
	
//	//관리자 수정 승인해서 업데이트 실행
//	@RequestMapping(value = "/kor/sdfac/testmap/OrderUpdateOk.do")
//	public String OrderUpdateOk(@RequestParam(value = "s_idxs", required = false) List<Integer> s_idxs,
//			@RequestParam int m_idx, @RequestParam int mn_idx, Model model,
//			@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		// 보안필터링 추가
//		commandMap = UtilCommon.checkMapData(commandMap, "");
//		HttpSession session = request.getSession(true);
//		session.setAttribute("nowMenuNo", ""); // 메뉴 세션 초기화
//
//		System.out.println("commandMap : " + commandMap);
//		
//		String user_id = (String) session.getAttribute("user_id");
//		String m_name = (String) session.getAttribute("m_name");
//		commandMap.put("m_email", user_id);
//		commandMap.put("m_name2", m_name);
//		
//		if(commandMap.get("delcheck") != null) {
//			int delcheck = Integer.parseInt((String) commandMap.get("delcheck"));
//
//			if (delcheck == 1) {
//				commandMap.put("opactiy", 0);
//			}else {
//				commandMap.put("opactiy", 1);
//			}
//		}else {
//			commandMap.put("opactiy", 1);
//		}
//		
//		// 요일 문자열 합치기
//		//String on_day_week = (String) commandMap.get("week_on_day1") + "~" + commandMap.get("week_on_day2");
//		//String on_day_weekend = (String) commandMap.get("weekend_on_day1") + "~" + commandMap.get("weekend_on_day2");
//		////System.out.println("on_day_Week " + on_day_week);
//		//System.out.println("on_day_Weekend " + on_day_weekend);
//		//commandMap.put("on_day_week", on_day_week);
//		//commandMap.put("on_day_weekend", on_day_weekend);
//		
//		
//		if(commandMap.get("filecheck") != null) {
//			int filecheck = Integer.parseInt((String) commandMap.get("filecheck"));
//			if(filecheck == 1){
//				try {
//					// 폴더 파일 저장 경로
//			  		Properties props = new Properties();
//			  		props.load(new java.io.BufferedInputStream( getClass().getClassLoader().getResourceAsStream("config.properties")) );
//			  		String strFilePath = props.getProperty("uploadsDir").trim();
//			  		String pathUrl = "/upload";
//			  		pathUrl += "/testmap";
//			  		strFilePath = strFilePath+"/testmap";
//			  		
//			  		Map<String, Map<String, String>> fileInfoMap = FileUtil.doMultiFileUpLoad(request, strFilePath, pathUrl);
//					if (fileInfoMap != null){
//						/* int i=1; */
//					    Iterator<String> iterator = fileInfoMap.keySet().iterator();
//					    while (iterator.hasNext()){
//					        Map<String, String> fileMap = new HashMap<String, String>();
//					        String key = iterator.next();
//					        fileMap = fileInfoMap.get(key);
//					        
//					        //뭐로 불러오는지 확인
//							
//							commandMap.put("filedname", fileMap.get("fieldname").toString());
//							commandMap.put("filetype", fileMap.get("filetype").toString()); 
//							commandMap.put("filename", fileMap.get("filename").toString());
//							commandMap.put("filepath", fileMap.get("filepath").toString()); 
//							commandMap.put("fileurlpath", fileMap.get("fileurlpath").toString()); 
//							commandMap.put("filesize", fileMap.get("filesize").toString()); 
//							commandMap.put("filerename", fileMap.get("filerename").toString());//이거로
//							//commandMap.put("file_num", i);
//							 
//					        
//					    }
//						
//					    System.out.println("commandMap1 "+commandMap.toString());	
//					    // 수정 제안의 내용 본 내용으로 수정
//						mapservice.mapOrderUpdateOk_con(commandMap);
//					}
//				} catch (Exception e) {
//					model.addAttribute("commandMap", commandMap);
//					model.addAttribute("alertMsg", "오류가 발생 하였습니다. 관리자에게 문의 바랍니다.");
//					model.addAttribute("gotoUrl", "/kor/sdfac/rent/application_write.do");
//					return "cms/common/alert";
//				}
//				
//			}else if (filecheck == 2) {
//				//기존파일을 사용하는 수정 제안의 내용 본 내용으로 수정
//				mapservice.mapOrderUpdateOk_OldFile(commandMap);
//			}
//		}else {
//			// 수정 제안의 내용 본 내용으로 수정
//			mapservice.mapOrderUpdateOk_con(commandMap);
//		}
//		
//		// int m_idx = (int) commandMap.get("m_idx");
//		// 기존 서비스 체크값 삭제
//		mapservice.mapDelete_checked_service(m_idx);
//		// s_idx 컨텐츠에 인서트
//		if (s_idxs != null) {
//			for (int s_idx : s_idxs) {
//				System.out.println("s_idx : " + s_idx);
//				Map<String, Object> checkedserviceMap = new HashMap<String, Object>();
//				checkedserviceMap.put("s_idx", s_idx);
//				checkedserviceMap.put("m_idx", m_idx);
//				System.out.println(checkedserviceMap.toString());
//				mapservice.checked_service_insert(checkedserviceMap);
//			}
//		}
//		// 업데이트 후 기존 제안 값 삭제
//		mapservice.mapDeleteOrder_New(mn_idx);
//		mapservice.mapDelete_checked_service_new(mn_idx);
//
//		return "redirect:/kor/sdfac/testmap/mapOrderList.do";
//	}
	
	
//	//관리자 제안 삭제
//	@RequestMapping(value = "/kor/sdfac/testmap/OrderDelete.do")
//	public String OrderDelete(@RequestParam int mn_idx, Model model,@RequestParam Map<String, Object> commandMap, 
//			HttpServletRequest request, HttpServletResponse response)throws Exception {
//		System.out.println("이거 삭제"+ mn_idx);
//		// 업데이트 후 기존 제안 값 삭제
//		mapservice.mapDeleteOrder_New(mn_idx);
//		mapservice.mapDelete_checked_service_new(mn_idx);
//		
//		//String msg = "";
//		//model.addAttribute("msg", msg);
//		
//		return "redirect:/kor/sdfac/testmap/mapOrderList.do";
//	}
	
	
}
