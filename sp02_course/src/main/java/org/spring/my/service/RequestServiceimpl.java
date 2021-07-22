package org.spring.my.service;

import java.util.List;
import java.util.Map;

import org.spring.my.dao.RequestDAO;
import org.spring.my.dto.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceimpl implements RequestService {

	@Autowired
	private RequestDAO requestdao;
	
	@Override
	public void insert(Request request) {
		
		requestdao.insert(request);
	}

	@Override
	public List<Map<String, Object>> selectlist(String ccode) {
		
		return requestdao.selectlist(ccode);
	}
	
}
