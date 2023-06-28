package com.spring.bittlebittle.board.service;
import com.spring.bittlebittle.board.vo.Board;

import java.util.List;

public interface BoardService {

    List<Board> getBoardList();
    Board getBoard(int boardNo);
    int addBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(int boardNo);

}
