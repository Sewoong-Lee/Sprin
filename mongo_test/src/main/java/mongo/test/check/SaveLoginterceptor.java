package mongo.test.check;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SaveLoginterceptor extends HandlerInterceptorAdapter {
	
	//앞에서 동작하는 메서드 - preHandle() , 뒤에서 동작하는 메서드 - postHandle
	// 처리를 하러 가기전에 앞에서 동작을 시킨다.
	//부모 재정의
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler ) throws Exception {
		
		System.out.println("SaveLoginterceptor.preHandle");
		
		// return이 true면 다음 계속 진행
		// false면 다음 진행
		// 다음 Interceptor 진행을 위해 부모객체의 preHandle() 호출 결과를 바로 리턴
		return super.preHandle(request, response, handler);
		
	}
	
}
