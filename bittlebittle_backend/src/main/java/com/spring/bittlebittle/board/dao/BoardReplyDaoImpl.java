package com.spring.bittlebittle.board.dao;

import com.spring.bittlebittle.board.vo.BoardReply;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardReplyDaoImpl implements BoardReplyDao{
    @Autowired
    private SqlSession sqlSession;

    private Logger log = LogManager.getLogger("case3");


    public List<BoardReply> getReplyList(int boardNo) {
        return sqlSession.selectList("boardReplyMapper.getReplyList", boardNo);
    }

    public BoardReply getReply(int replyNo) {
        return sqlSession.selectOne("boardReplyMapper.getReply", replyNo);
    }

    public void addReply(BoardReply reply) {
        sqlSession.insert("boardReplyMapper.addReply", reply);
    }

    public void updateReply(BoardReply reply) {
        log.debug(reply.toString());
        sqlSession.update("boardReplyMapper.updateReply", reply);
    }

    public void deleteReply(int replyNo) {
        sqlSession.update("boardReplyMapper.deleteReply", replyNo);
    }

}
