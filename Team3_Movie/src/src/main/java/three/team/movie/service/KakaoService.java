package three.team.movie.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import three.team.movie.controller.CinemaController;
import three.team.movie.dao.CinemaDAO;
import three.team.movie.dto.KakaoPayApprovalVO;
import three.team.movie.dto.KakaoPayReadyVO;
import three.team.movie.dto.Mv_sales;

@Service
public class KakaoService {
	private static final String HOST = "https://kapi.kakao.com";
	private static final Logger logger = LoggerFactory.getLogger(KakaoService.class);


    private static KakaoPayReadyVO kakaoPayReadyVO;
    private static KakaoPayApprovalVO kakaoPayApprovalVO;
    
    @SuppressWarnings("deprecation")
	public String kakaoPayReady(Mv_sales mv_sales) {
 
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "c32906fe9366b61256b9d3b8c0f9021f");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", Integer.toString(mv_sales.getSal_num()));  //주문번호
        params.add("partner_user_id", mv_sales.getUser_id()); //회원 아이디
        params.add("item_name", "영화 예매권");  //상품명
        params.add("quantity", Integer.toString(mv_sales.getTickets()));  //수량
        params.add("total_amount", Integer.toString(mv_sales.getPrice()));  //총 금액
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost:8081/movie/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8081/movie/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8081/movie/kakaoPaySuccessFail");
 
         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
 
        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
            
            logger.info("" + kakaoPayReadyVO);
            
            return kakaoPayReadyVO.getNext_redirect_pc_url();
 
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "/kakao";
        
    }
    
    public static KakaoPayApprovalVO kakaoPayInfo(String pg_token, Mv_sales mv_sales) {
    	 
    	logger.info("KakaoPayInfoVO............................................");
    	logger.info("-----------------------------");
        
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "c32906fe9366b61256b9d3b8c0f9021f");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
 
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", Integer.toString(mv_sales.getSal_num()));
        params.add("partner_user_id", mv_sales.getUser_id()); //회원 아이디
        params.add("pg_token", pg_token);
        params.add("total_amount", Integer.toString(mv_sales.getPrice()));  //총 금액
        
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            logger.info("" + kakaoPayApprovalVO);
        
            return kakaoPayApprovalVO;
        
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    
}
