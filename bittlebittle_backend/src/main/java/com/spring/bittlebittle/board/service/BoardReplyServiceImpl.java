package com.spring.bittlebittle.board.service;

import com.spring.bittlebittle.board.dao.BoardReplyDaoImpl;
import com.spring.bittlebittle.board.vo.BoardReply;
import com.spring.bittlebittle.user.dao.UserDao;
import com.spring.bittlebittle.user.vo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardReplyServiceImpl implements BoardReplyService{

    @Autowired
    private BoardReplyDaoImpl dao;
    @Autowired
    private UserDao userDao;

    public List<BoardReply> getReplyList(int boardNo) {
        return dao.getReplyList(boardNo);
    }

//    @Override
//    public void addReply(BoardReply reply) {
//        User user = userDao.selectUsers().get(0);
//        reply.setUserNo(user.getUserNo());
////        dao.addReply(reply, userNo, nickname);
//        dao.addReply(reply);
//    }

    @Override
    public void addReply(BoardReply boardReply) {
        dao.addReply(boardReply);
    }

    @Override
    public void updateReply(BoardReply reply) {
    	dao.updateReply(reply);
    }

    @Override
    public void deleteReply(int replyNo) {
    	dao.deleteReply(replyNo);
    }

//    @Override
//    public boolean isAuthor(int replyNo, int userNo) {
//        return false;
//    }


}
