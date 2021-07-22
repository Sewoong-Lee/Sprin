package org.spring.my.dto;

import lombok.Data;

@Data
public class Item {
	private String itemcode;
	private String itemname;
	private int price;
	private String regdate;
}
