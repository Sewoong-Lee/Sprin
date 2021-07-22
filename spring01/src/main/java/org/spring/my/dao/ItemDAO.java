package org.spring.my.dao;

import java.util.List;

import org.spring.my.dto.Item;

public interface ItemDAO {

	public List<Item> selectlist(String itemcode);

}
