package com.spring.bittlebittle.bottle.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BottleAll {

	private int bottleNo;
	private String bottleName;
	private String bottleContent;
	private String bottleBrand;
	private double bottleAbv;
	private int viewCnt;
	private String createDate;
	private String imgUrl;
	private String imgCusUrl;
	private String status;
	private int reviewCount;
	private int grade;
}
