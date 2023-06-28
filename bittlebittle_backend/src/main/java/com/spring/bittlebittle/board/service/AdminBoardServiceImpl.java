package com.spring.bittlebittle.board.service;

import com.spring.bittlebittle.board.dao.BoardDaoImpl;
import com.spring.bittlebittle.board.vo.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AdminBoardServiceImpl implements AdminBoardService{
    @Autowired
    private BoardDaoImpl dao;

    @Override
    public List<Board> getBoardList() {
        return dao.getBoardList();
    }

    @Override
    public Board getBoard(int boardNo) {
        return dao.getBoard(boardNo);
    }

    @Override
    public void addBoard(Board board) {
        dao.addBoard(board);
    }

    @Override
    public void updateBoard(Board board) {
        int boardNo = board.getBoardNo();
        int userNo = board.getUserNo();
//        if (dao.isAuthor(boardNo, userNo)) {
//            dao.updateBoard(board);
//
//        }
    }

    @Override
    public void deleteBoard(int boardNo, int userNo) {
//        if (dao.isAuthor(boardNo, userNo)) {
//            dao.deleteBoard(boardNo);
//        }
    }
}
