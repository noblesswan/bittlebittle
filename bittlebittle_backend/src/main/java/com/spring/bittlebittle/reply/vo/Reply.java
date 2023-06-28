package com.spring.bittlebittle.reply.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

	private int replyNo;
	private int userNo;
	private int reviewNo;
	private String replyContent;
	private String createTime;
	private String status;
	
}
