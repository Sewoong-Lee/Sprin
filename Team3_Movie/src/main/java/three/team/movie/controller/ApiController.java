package three.team.movie.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ApiController {
	
	@GetMapping("api")
	public void MoviApi() {
		System.out.println("zz");
		List<Map<String, String>> jlist = new ArrayList<Map<String, String>>();
		try {
			 String key = "d19a0eec1d9a4df8f38e93c8cb93c0b7";
		        StringBuilder urlBuilder = new StringBuilder("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json"); /*URL*/
		        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "="+key); /*Service Key*/
		        //urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
		        urlBuilder.append("&" + URLEncoder.encode("movieCd","UTF-8") + "=" + URLEncoder.encode("20124079", "UTF-8")); /*영화 코드*/
		        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미터 resultType=json 입력*/
		        URL url = new URL(urlBuilder.toString());
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type", "application/json");
		        System.out.println("Response code: " + conn.getResponseCode());
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		        }
		        StringBuilder sb = new StringBuilder();
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
		        System.out.println(sb.toString());
		        
		        //---------------------------------------------------------
		        String data = sb.toString();
		        System.out.println("1 데이터:"+data);
		        
		        //json파싱
		        //org.제이슨.심플로 시작하는 애들로 임포트
		        //{로 시작하면 제이슨 오브젝트 [시작하면 제이슨 어레이
		        JSONParser parser = new JSONParser();
		        //{로 시작하니 오브젝트로
		        JSONObject object =  (JSONObject) parser.parse(data);
		        System.out.println("2 오브젝트:"+object);
		        //[로 시작하니 어레이로
		        JSONObject object2 =  (JSONObject) object.get("movieInfoResult");
		        System.out.println("3 오브젝트:"+object2);
		        
		        JSONObject object3 =  (JSONObject) object2.get("movieInfo");
		        System.out.println("3 오브젝트:"+object3);
		        
		        JSONArray directors =  (JSONArray) object3.get("directors");
		        System.out.println("3 오브젝트:"+directors);
//		        JSONArray  arry= (JSONArray)object2.get("item");
//		        //System.out.println("4 어레이:"+arry);
		        
		       
		        //다시 {로 시작하니 오브젝트로
		        //반복 부분
//		        for(int i =0 ; i<object3.size() ; i++) {
//		        	//System.out.println("순번"+i+"-------------------------------------------------------------");
//		        	Map<String, String> jmap = new HashMap<>();
//		        	object = (JSONObject) object3.get(i);
//		        	//System.out.println("5 어레이에서 오브젝트:"+object);
//		        	
//		        	Set<String> kset = object.keySet(); //object의 키 
//		            //System.out.println("keySet:"+object.keySet());
//		            
//		            //모든 키와 값을 맵에 넣음
//		            for(String key : kset) {
//		            	jmap.put(key, (String) object.get(key));
//		            	//System.out.println("1 jmap:"+jmap);
//		            }
////		        	
//		            jlist.add(jmap);
//	
//		        }
		System.out.println();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("예외");
		}
		
	}
//	@GetMapping("api2")
//	public void MoviApi2() {
//		String clientID = "";
//		String clientSecret = "";
//		String text = null;
//		
//		try {
//			text = URLEncoder.encode("검색어", "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			System.out.println("검색어 실패");
//			e.printStackTrace();
//		}
//		
//		String apiURL = "https://openapi.naver.com/v1/search/movie.json?query="+text;
//		Map<String, String> requestHeaders = new HashMap<String, String>();
//		requestHeaders.put("X-Naver-Client-id", clientID);
//		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
//		
//		String responseBody = get(apiURL, requestHeaders);
//		String json = responseBody;
//		
//		JSONParser parser = new JSONParser();
//		JSONObject obj = (JSONObject) parser.parse(json);
//		JSONArray item = (JSONArray)obj.get("item");
//		List<Map<String, String>> mlist = new ArrayList<>();
//		
//		for (int i=0; i < item.size(); i++) {
//			Map<String, String> m = new HashMap<>();
//			JSONObject tmp = (JSONObject)item.get(i);
//			String title = (String) tmp.get("title");
//			String image = (String) tmp.get("image");
//			String description = (String) tmp.get("description");
//			
//			m.put("title", title);
//			m.put("image", image);
//			m.put("description", description);
//			
//			mlist.add(m);
//		}
//		System.out.println(mlist);
//		
//		
//		
//		
//	}
	
//	@GetMapping("api3")
//	public void MoviApi3() {
//		// 베이스 URL 
//		final String baseUrl = "https://openapi.naver.com/v1/search/blog.json?query="; 
//		
//		public String search(String clientId, String secret, String _url) { 
//				HttpURLConnection con = null; 
//				String result = "";
//			try { 
//				URL url = new URL(baseUrl + _url); 
//				con = (HttpURLConnection) url.openConnection(); 
//				
//				con.setRequestMethod("GET"); 
//				con.setRequestProperty("X-Naver-Client-Id", clientId); 
//				con.setRequestProperty("X-Naver-Client-Secret", secret); 
//				
//				int responseCode = con.getResponseCode(); 
//				if (responseCode == HttpURLConnection.HTTP_OK) result = readBody(con.getInputStream()); 
//				else result = readBody(con.getErrorStream()); 
//			
//			} catch (Exception e) { 
//				System.out.println("연결 오류 : " + e); 
//			} finally { 
//				con.disconnect(); 
//			} return result; 
//		} 
//		/** * 결과를 읽는다 * * @param body * @return */ 
//		public String readBody(InputStream body){ 
//			InputStreamReader streamReader = new InputStreamReader(body); 
//			try (BufferedReader lineReader = new BufferedReader(streamReader)) { 
//				StringBuilder responseBody = new StringBuilder(); String line; while ((line = lineReader.readLine()) != null) { responseBody.append(line); } return responseBody.toString(); } catch (IOException e) { throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e); } }

	//}
	
	@GetMapping("api4")
	public void MoviApi4() {
		System.out.println("zz");
		List<Map<String, String>> jlist = new ArrayList<Map<String, String>>();
		try {
			 String key = "영화";
			 String clientId = "ilx3xdehMDtutYNmRQOJ";
			 String secret = "WNXl1nF5I8";
			 
		        StringBuilder urlBuilder = new StringBuilder("https://openapi.naver.com/v1/search/movie.json"); /*URL*/
		        urlBuilder.append("?" + URLEncoder.encode("query","UTF-8") + "="+key); /*Service Key*/
		        urlBuilder.append("&" + URLEncoder.encode("display","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
		        urlBuilder.append("&" + URLEncoder.encode("start","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*영화 코드*/
		        urlBuilder.append("&" + URLEncoder.encode("genre","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*영화 코드*/
		        //urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미터 resultType=json 입력*/
		        URL url = new URL(urlBuilder.toString());
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
				conn.setRequestProperty("X-Naver-Client-Id", clientId); 
				conn.setRequestProperty("X-Naver-Client-Secret", secret); 
		        //conn.setRequestProperty("Content-type", "application/json");
		        //System.out.println("Response code: " + conn.getResponseCode());
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		        }
		        StringBuilder sb = new StringBuilder();
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
		        System.out.println(sb.toString());
		        
		        //---------------------------------------------------------
		        String data = sb.toString();
		        System.out.println("1 데이터:"+data);
		        
		        
		        JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(data);
				JSONArray item = (JSONArray)obj.get("item");
				List<Map<String, String>> mlist = new ArrayList<>();
				
				for (int i=0; i < item.size(); i++) {
					Map<String, String> m = new HashMap<>();
					JSONObject tmp = (JSONObject)item.get(i);
					String title = (String) tmp.get("title");
					String image = (String) tmp.get("image");
					String description = (String) tmp.get("description");
					
					m.put("title", title);
					m.put("image", image);
					m.put("description", description);
					
					mlist.add(m);
				}
		System.out.println(mlist);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("예외");
		}
		
	}
	
}
