package org.spring.my.service;

import java.util.List;
import java.util.Map;

import org.spring.my.dto.Request;

public interface RequestService {

	void insert(Request request);

	List<Map<String, Object>> selectlist(String ccode);

}
