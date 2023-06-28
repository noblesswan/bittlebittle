package com.spring.bittlebittle.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.board.vo.Board;

@Repository
public class AdminBoardDaoImpl implements AdminBoardDao{

	
	@Autowired
    private SqlSession sqlSession;

//게시판전체조회
    public List<Board> getBoardList() {
        return sqlSession.selectList("BoardMapper.getBoardList");
    }
//상세조회
    public Board getBoard(int boardNo) {
        return sqlSession.selectOne("BoardMapper.getBoard", boardNo);
    }
//보드추가
    public void addBoard(Board board) {
        sqlSession.insert("BoardMapper.adminAddboard", board);
    }
//보드수정
    public void updateBoard(Board board) {
        sqlSession.update("BoardMapper.updateBoard", board);
    }
//보드삭제
    public void deleteBoard(int boardNo) {
        sqlSession.delete("BoardMapper.deleteBoard", boardNo);
    }
//작성자여부
//    public boolean isAuthor(int boardNo, int userNo) {
//        Map<String, Integer> params = new HashMap<>();
//        params.put("boardNo", boardNo);
//        params.put("userNo", userNo);
//        Integer count = sqlSession.selectOne("BoardMapper.isAuthor", params);
//        return count != null && count > 0;
//    }
}
