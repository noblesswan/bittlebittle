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

//�Խ�����ü��ȸ
    public List<Board> getBoardList() {
        return sqlSession.selectList("BoardMapper.getBoardList");
    }
//����ȸ
    public Board getBoard(int boardNo) {
        return sqlSession.selectOne("BoardMapper.getBoard", boardNo);
    }
//�����߰�
    public void addBoard(Board board) {
        sqlSession.insert("BoardMapper.adminAddboard", board);
    }
//�������
    public void updateBoard(Board board) {
        sqlSession.update("BoardMapper.updateBoard", board);
    }
//�������
    public void deleteBoard(int boardNo) {
        sqlSession.delete("BoardMapper.deleteBoard", boardNo);
    }
//�ۼ��ڿ���
//    public boolean isAuthor(int boardNo, int userNo) {
//        Map<String, Integer> params = new HashMap<>();
//        params.put("boardNo", boardNo);
//        params.put("userNo", userNo);
//        Integer count = sqlSession.selectOne("BoardMapper.isAuthor", params);
//        return count != null && count > 0;
//    }
}
