//package neo.com.kor.sdfac.testmap.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import neo.com.kor.sdfac.testmap.service.mapService;
//
//@Service("mapService")
//public class mapServiceimpl implements mapService {
//	@Resource(name = "mapDAO")
//    private mapDAO mapdao;
//	
//	
//	@Override
//	public List<Map<String, Object>> selectSerch(Map<String, Object> mapmap) throws Exception {
//		System.out.println("서비스 들어옴");
//		return mapdao.selectSerch(mapmap);
//	}
//
//
//	@Override
//	public List<Map<String, Object>> checkedselectSerch(Map<String, Object> mapmap) {
//		// TODO Auto-generated method stub
//		return mapdao.checkedselectSerch(mapmap);
//	}
//
//
//	@Override
//	public void mapInsert(Map<String, Object> commandMap) {
//		// TODO Auto-generated method stub
//		mapdao.mapInsert(commandMap);
//	}
//
//}
