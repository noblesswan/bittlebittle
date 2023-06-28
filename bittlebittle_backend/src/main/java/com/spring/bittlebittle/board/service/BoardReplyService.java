package com.spring.bittlebittle.board.service;

import com.spring.bittlebittle.board.vo.BoardReply;

import java.util.List;

public interface BoardReplyService {


    List<BoardReply> getReplyList(int reviewNo);

    void addReply(BoardReply boardReply);

    void updateReply(BoardReply boardReply);

    void deleteReply(int replyNo);

}
