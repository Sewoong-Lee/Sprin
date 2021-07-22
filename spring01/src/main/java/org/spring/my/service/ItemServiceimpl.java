package org.spring.my.service;

import java.util.List;

import org.spring.my.dao.ItemDAO;
import org.spring.my.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceimpl implements ItemService{
	
	@Autowired
	private ItemDAO itemdao;
	
	@Override
	public List<Item> selectlist(String itemcode) {
		
		return itemdao.selectlist(itemcode);
	}

}
