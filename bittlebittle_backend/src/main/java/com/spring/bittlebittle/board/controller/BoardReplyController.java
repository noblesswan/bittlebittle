package com.spring.bittlebittle.board.controller;

import com.spring.bittlebittle.board.service.BoardReplyService;
import com.spring.bittlebittle.board.vo.BoardReply;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/boards/{boardNo}/replies")
public class BoardReplyController {
    @Autowired
    private BoardReplyService service;

    @Autowired
    private JwtUtil jwtUtil;

    private Logger log = LogManager.getLogger("case3");


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BoardReply> replyList(@PathVariable int boardNo) {
        return service.getReplyList(boardNo);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addReply(@PathVariable int boardNo, @RequestBody BoardReply boardReply, HttpServletRequest request) {
        String token = jwtUtil.resolveAccessToken(request);
        // access token 과 refreshtokenIdx 를 가지고 조건 검사. 리턴 타입은 boolean
        if(jwtUtil.validateToken(token, UserJwt.builder()
                .userJwtIdx(jwtUtil.resolveRefreshToken(request))
                .build())) {
            // 토큰이 유효하다면 유저 정보 조회

            boardReply.setBoardNo(boardNo);
            service.addReply(boardReply);

            Map<String, Object> map = new HashMap<>();
            map.put("request", true);
            return ResponseEntity.ok().body(map);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("token", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }

    @PostMapping(value = "/set-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> editReply(@RequestBody BoardReply boardReply, HttpServletRequest request) {

        log.debug("댓글 수정");

        String token = jwtUtil.resolveAccessToken(request);
        // access token 과 refreshtokenIdx 를 가지고 조건 검사. 리턴 타입은 boolean
        if(jwtUtil.validateToken(token, UserJwt.builder()
                .userJwtIdx(jwtUtil.resolveRefreshToken(request))
                .build())) {
            // 토큰이 유효하다면 유저 정보 조회

            service.updateReply(boardReply);

            Map<String, Object> map = new HashMap<>();
            map.put("request", true);
            return ResponseEntity.ok().body(map);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("token", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }

    @GetMapping(value = "/{replyNo}/deletion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> removeReply(@PathVariable int boardNo, @PathVariable int replyNo, HttpServletRequest request) {

        log.debug("댓글 삭제");

        String token = jwtUtil.resolveAccessToken(request);
        // access token 과 refreshtokenIdx 를 가지고 조건 검사. 리턴 타입은 boolean
        if(jwtUtil.validateToken(token, UserJwt.builder()
                .userJwtIdx(jwtUtil.resolveRefreshToken(request))
                .build())) {
            // 토큰이 유효하다면 유저 정보 조회
            service.deleteReply(replyNo);

            Map<String, Object> map = new HashMap<>();
            map.put("request", true);
            return ResponseEntity.ok().body(map);

        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("token", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }
}

