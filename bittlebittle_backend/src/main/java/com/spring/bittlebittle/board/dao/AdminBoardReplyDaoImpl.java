package com.spring.bittlebittle.board.dao;

import com.spring.bittlebittle.board.vo.BoardReply;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AdminBoardReplyDaoImpl implements AdminBoardReplyDao{
    @Autowired
    private SqlSession sqlSession;

//    public List<BoardReply> getReplyList(int reviewNo) {
//        return sqlSession.selectList("ReplyMapper.getReplyList", reviewNo);
//    }

    public List<BoardReply> getReplyList(int replyNo) {
        return sqlSession.selectOne("boardReplyMapper.getReplyList", replyNo);
    }

    public void addReply(BoardReply reply, int userNo, String nickname) {
    	Map<String, Object> params = new HashMap<>();
        params.put("reply", reply);
        params.put("userNo", userNo);
        params.put("nickname", nickname);
        sqlSession.insert("boardReplyMapper.addReply", params);
    }

//    public void updateReply(BoardReply reply) {
//        sqlSession.update("boardReplyMapper.updateReply", reply);
//    }

    public void deleteReply(int replyNo) {
        sqlSession.update("boardReplyMapper.deleteReply", replyNo);
    }

//    public boolean isAuthor(int replyNo, int userNo) {
//        Map<String, Integer> params = new HashMap<>();
//        params.put("replyNo", replyNo);
//        params.put("userNo", userNo);
//        Integer count = sqlSession.selectOne("boardReplyMapper.isAuthor", params);
//        return count != null && count > 0;
//    }

	@Override
	public boolean isAdmin(String adminYn) {
		// TODO Auto-generated method stub
		return false;
	}

	

//	@Override
//	public void addDate(BoardReply reply) {
//		// TODO Auto-generated method stub
//		
//		
//	}

}
