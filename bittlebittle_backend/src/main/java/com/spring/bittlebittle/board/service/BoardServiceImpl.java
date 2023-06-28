package com.spring.bittlebittle.board.service;

import com.spring.bittlebittle.board.dao.BoardDao;
import com.spring.bittlebittle.board.vo.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardDao dao;

    @Override
    public List<Board> getBoardList() {
        return dao.getBoardList();
    }

    @Override
    public Board getBoard(int boardNo) {
        return dao.getBoard(boardNo);
    }

    @Override
    public int addBoard(Board board) {
        return dao.addBoard(board);
    }

    @Override
    public void updateBoard(Board board) {
        dao.updateBoard(board);
    }

    @Override
    public void deleteBoard(int boardNo) {
        dao.deleteBoard(boardNo);
    }
}
