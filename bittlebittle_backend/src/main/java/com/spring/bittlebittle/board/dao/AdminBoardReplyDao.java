package com.spring.bittlebittle.board.dao;

import com.spring.bittlebittle.board.vo.BoardReply;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AdminBoardReplyDao {

    List<BoardReply> getReplyList(int replyNo) ; // ��ȸ
//    BoardReply getReply(int replyNo);
    void addReply(BoardReply reply, int userNo, String nickname); //���� => �̶� nickname, userNo ���� �־��ֱ�
//    void updateReply(BoardReply reply); //����
    void deleteReply(int replyNo); //����
    boolean isAdmin(String adminYn); // �����ڿ���
    

}
