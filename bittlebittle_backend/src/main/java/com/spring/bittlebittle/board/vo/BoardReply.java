package com.spring.bittlebittle.board.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardReply {

	private int replyNo;
	private int userNo;
	private int boardNo;
	private String nickname;
	private String createDate;
	private String replyContent;
	private String status;

}