package com.spring.bittlebittle.board.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

	private int boardNo;
	private int userNo;
	private String boardTitle;
	private String nickname;
	private String createDate;
	private String boardContent;
	private String status;
	
	
}