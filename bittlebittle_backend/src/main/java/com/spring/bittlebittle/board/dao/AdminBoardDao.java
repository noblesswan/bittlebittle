package com.spring.bittlebittle.board.dao;

import com.spring.bittlebittle.board.vo.Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AdminBoardDao {


    List<Board> getBoardList();
    Board getBoard(int boardNo);
    void addBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(int boardNo);
//    boolean isAuthor(int boardNo, int userNo);
}
