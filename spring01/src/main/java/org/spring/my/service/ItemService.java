package org.spring.my.service;

import java.util.List;

import org.spring.my.dto.Item;

public interface ItemService {

	public List<Item> selectlist(String itemcode);

}
