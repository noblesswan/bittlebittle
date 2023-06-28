package com.spring.bittlebittle.board.dao;

import com.spring.bittlebittle.board.vo.Board;

import java.util.List;

public interface BoardDao {


    List<Board> getBoardList();
    Board getBoard(int boardNo);

    int addBoard(Board board);

    int updateBoard(Board board);
    int deleteBoard(int boardNo);

//    boolean isAuthor(int boardNo, int userNo);
}
