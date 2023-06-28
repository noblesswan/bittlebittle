package com.spring.bittlebittle.bottle.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BottleSearch {

	private String keyword;
	private String sorted;
	private int[] tagNoList;
  }