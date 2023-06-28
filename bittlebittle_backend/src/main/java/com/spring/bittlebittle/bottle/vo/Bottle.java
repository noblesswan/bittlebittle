package com.spring.bittlebittle.bottle.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bottle {

	private int bottleNo;
	private String bottleName;
	private String bottleContent;
	private String bottleBrand;
	private double bottleAbv;
	private int viewCnt;

	private double grade;
	private String createDate;
	private String imgUrl;
	private String imgCusUrl;
	private String status;

}
