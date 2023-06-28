package com.spring.bittlebittle.food.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodInfo {
	
	private int foodNo;
	private String foodName;
	private String imgUrl;
	private String imgCusUrl;
	private int[] tagNoList;
}
