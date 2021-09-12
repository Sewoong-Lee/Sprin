package three.team.movie.service;

import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import three.team.movie.dao.MovieDataDAO;
import three.team.movie.dto.Mv_movie_data;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;



@Service
public class MovieApiServiceImpl implements MovieApiService{
	 
	@Autowired
	private MovieDataDAO movieDataDAO;
	
	@Override
	public void MovieApiCall(String codeName) {
		   String clientId = "OgblTcTV2ly92vForMoY"; //애플리케이션 클라이언트 아이디값"
	        String clientSecret = "F9e82hO47n"; //애플리케이션 클라이언트 시크릿값"

	        String text = null;
	        try {
	            text = URLEncoder.encode(codeName, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("검색어 인코딩 실패",e);
	        }
	        				
	        String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text;    // json 결과
	        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        String responseBody = get(apiURL,requestHeaders);

	        System.out.println(responseBody);
	        
	        //json 파싱
	        JSONParser jsonParser = new JSONParser();
	        
	        try {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
				System.out.println("2=="+jsonObject);
				
				JSONArray array =(JSONArray)jsonObject.get("items");
				jsonObject = (JSONObject) array.get(0);
				
				System.out.println("3"+array);
				
				System.out.println("-------------------");
				
				for(int i=0; i<array.size(); i++) {
					jsonObject = (JSONObject) array.get(i);
					Mv_movie_data mv_movie_data = new Mv_movie_data();
					
					System.out.println("--------------------------");
					System.out.println("한글제목:"+jsonObject.get("title"));
					System.out.println("영문제목:"+jsonObject.get("subtitle"));
					System.out.println("배우:"+jsonObject.get("actor"));
					System.out.println("이미지링크:"+jsonObject.get("image"));
					System.out.println("감독:"+jsonObject.get("director"));
					System.out.println("제작년도:"+jsonObject.get("pubDate"));
					System.out.println("영화평점:"+jsonObject.get("userRating"));
					System.out.println("링크:"+jsonObject.get("link"));
					System.out.println("------------------------");
					
					mv_movie_data.setMovie_name((String)jsonObject.get("title"));
					mv_movie_data.setActor_name((String)jsonObject.get("actor"));
					mv_movie_data.setMovie_poster_link((String)jsonObject.get("image"));
					mv_movie_data.setDirector((String)jsonObject.get("director"));
					
					movieDataDAO.insert(mv_movie_data);
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
	   
		}
	
    private String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
		
}
	
	
