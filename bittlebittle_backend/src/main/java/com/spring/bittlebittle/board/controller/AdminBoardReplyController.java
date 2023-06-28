package com.spring.bittlebittle.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.bittlebittle.board.service.AdminBoardReplyService;
import com.spring.bittlebittle.board.service.BoardReplyService;
import com.spring.bittlebittle.board.vo.BoardReply;
import com.spring.bittlebittle.user.vo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
public class AdminBoardReplyController {
	@Autowired
	private AdminBoardReplyService adminService;

//    //댓글 조회
//    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<BoardReply> replyList(@PathVariable int replyNo) {
//        return service.getReplyList(replyNo);
//    }
//    //댓글입력
//    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void addReply(@PathVariable int replyNo, @RequestBody BoardReply reply) {
//        reply.setReplyNo(replyNo);
//        service.addReply(reply);
//    }
//    //댓글수정
//    @PutMapping(value = "/{replyNo}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void updateReply(@PathVariable int replyNo,@RequestBody BoardReply reply) {
////        boardReply.setReviewNo(reviewNo);
//        reply.setReplyNo(replyNo);
//        service.updateReply(reply);
//    }
//    //댓글삭제
//    @DeleteMapping(value = "/{replyNo}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void deleteReply(@PathVariable int replyNo, @RequestParam int userNo) {
//        service.deleteReply(replyNo, userNo);
//
//    }

	// 댓글 목록 조회
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BoardReply>> getReplyList(@RequestParam int boardNo) {
		List<BoardReply> replyList = adminService.getReplyList(boardNo);
		return ResponseEntity.ok(replyList);
	}

	// 댓글 작성
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> addReply(@RequestBody BoardReply reply, @RequestParam int boardNo,
			@AuthenticationPrincipal User user) {
		Map<String, Object> resultMap = new HashMap<>();
		String nickname = user.getNickname();
		int userNo = user.getUserNo();
		adminService.addReply(reply, userNo, nickname);
		resultMap.put("status", "success");
		return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
	}

//    // 댓글 수정
//    @PutMapping(value = "/{replyNo}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Map<String, Object>> updateReply(@PathVariable int replyNo,
//                                                            @RequestBody BoardReply reply,
//                                                            @AuthenticationPrincipal User user) {
//        Map<String, Object> resultMap = new HashMap<>();
//        if (service.isAuthor(replyNo, user.getUserNo())) {
//            service.updateReply(reply);
//            resultMap.put("status", "success");
//            return ResponseEntity.ok(resultMap);
//        } else {
//            resultMap.put("status", "fail");
//            resultMap.put("message", "본인이 작성한 댓글만 수정할 수 있습니다.");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultMap);
//        }
//    }

	// 댓글 삭제
	@DeleteMapping(value = "/{replyNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> deleteReply(@PathVariable int replyNo,
			@AuthenticationPrincipal User user) {
		Map<String, Object> resultMap = new HashMap<>();
		if (adminService.isAdmin(user.getAdminYn())) {
			adminService.deleteReply(replyNo);
			resultMap.put("status", "success");
			return ResponseEntity.ok(resultMap);
		} else {
			resultMap.put("status", "fail");
			resultMap.put("message", "관리자만 댓글을 삭제할 수 있습니다.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultMap);
		}
	}
}
