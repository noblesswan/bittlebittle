package com.spring.bittlebittle.board.controller;

import java.util.List;
//import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.board.service.AdminBoardServiceImpl;
import com.spring.bittlebittle.board.service.BoardServiceImpl;
import com.spring.bittlebittle.board.vo.Board;

//import com.spring.bittlebittle.NotAuthorizedException;

@RestController
@RequestMapping("/admin/freeboard")
public class AdminBoardController {
    @Autowired
    private AdminBoardServiceImpl adminboardService;

    //자유게시판 조회
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Board> boardList() {
        return adminboardService.getBoardList();
    }
    //자유게시판 상세조회
    @GetMapping(value = "/{boardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> boardDetail(@PathVariable int boardNo) {
        Board board = adminboardService.getBoard(boardNo);
        if (board != null) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //자유게시판 입력(추가)
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> addBoard(@RequestBody Board board) {
    	adminboardService.addBoard(board);
        return new ResponseEntity<>(board, HttpStatus.CREATED);
    }
    //자유게시판 수정
    @PutMapping(value = "/{boardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> updateBoard(@PathVariable int boardNo, @RequestBody Board board) {
        board.setBoardNo(boardNo);

        adminboardService.updateBoard(board);
        return new ResponseEntity<>(board, HttpStatus.OK);

    }
    //자유게시판 삭제
    @DeleteMapping(value = "/{boardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteBoard(@PathVariable int boardNo, @RequestParam int userNo) {
//        try {
    	adminboardService.deleteBoard(boardNo, userNo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (NotAuthorizedException e) {
//            return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
//        }
    }
}