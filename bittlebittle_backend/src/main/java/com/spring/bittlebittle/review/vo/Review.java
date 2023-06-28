package com.spring.bittlebittle.review.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

	private int reviewNo;
	private int userNo;
	private int bottleNo;
	private int grade;
	private String reviewTitle;
	private String reviewContent;
	private String createDate;
	private String imgUrl;
	private String imgCusUrl;
	private String status;
}
