package com.spring.bittlebittle.faq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Faq 
{
	private int faqNo, userNo;
	private String faqTitle, faqContent, status;
}
