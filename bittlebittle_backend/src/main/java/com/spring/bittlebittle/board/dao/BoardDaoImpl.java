package com.spring.bittlebittle.board.dao;

import com.spring.bittlebittle.board.vo.Board;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
    private SqlSession sqlSession;

    private Logger log = LogManager.getLogger("case3");


    public List<Board> getBoardList() {
        log.debug("Dada");
        return sqlSession.selectList("boardMapper.getBoards");
    }

    public Board getBoard(int boardNo) {
        return sqlSession.selectOne("boardMapper.getBoard", boardNo);
    }

    public int addBoard(Board board) {
        return sqlSession.insert("boardMapper.addBoard", board);
    }

    public int updateBoard(Board board) {
        log.debug(board.toString());
        return sqlSession.update("boardMapper.updateBoard", board);
    }

    public int deleteBoard(int boardNo) {
        return sqlSession.update("boardMapper.deleteBoard", boardNo);
    }

//    public boolean isAuthor(int boardNo, int userNo) {
//        Map<String, Integer> params = new HashMap<>();
//        params.put("boardNo", boardNo);
//        params.put("userNo", userNo);
//        Integer count = sqlSession.selectOne("BoardMapper.isAuthor", params);
//        return count != null && count > 0;
//    }
}

