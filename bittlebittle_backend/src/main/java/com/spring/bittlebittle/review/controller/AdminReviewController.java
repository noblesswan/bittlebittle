package com.spring.bittlebittle.review.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.reply.service.ReplyService;
import com.spring.bittlebittle.reply.vo.ReplyNickname;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.review.vo.ReviewNickname;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.JwtUtil;

@RestController
@RequestMapping(value="/api/admin/bottles/{bottleNo}/reviews", produces="application/json; charset=UTF-8")
public class AdminReviewController {

	@Autowired
	private ReviewService rservice;
	@Autowired
	private ReplyService rpservice;
	@Autowired
	private JwtUtil jwtUtil;
	
	Logger log = LogManager.getLogger("case3");
	
	// 리뷰목록조회(확인완료)
	@GetMapping
	public List<ReviewNickname> getReviewList(@PathVariable int bottleNo){
		
		List<ReviewNickname> reviewList = rservice.getReviews(bottleNo);
		
		
		return reviewList;
	}
	
	// 개별리뷰조회(확인완료)
	@GetMapping(value="/{reviewNo}")
	public Map<String, Object> getReview(@PathVariable int reviewNo) {
		
		Map<String, Object> map = rservice.getReview(reviewNo);
		
		return map;
	}
	
	// 리뷰삭제 (확인완료)
	@GetMapping(value="/{reviewNo}/deletion")
	public List<ReviewNickname> removeReview(@PathVariable int bottleNo, @PathVariable int reviewNo, HttpServletRequest request){
		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {
			
			List<ReviewNickname> reviewList = rservice.removeReview(bottleNo, reviewNo);
			
			return reviewList;
			
		} else {
			
			List<ReviewNickname> reviewList = rservice.getReviews(bottleNo);
			
			return reviewList;
		}
		
		
	}
	
	// 리뷰댓글조회  (확인완료)
	@GetMapping(value="/{reviewNo}/replies")
	public List<ReplyNickname> getReplies(@PathVariable int reviewNo){
			
		List<ReplyNickname> replyList = rpservice.getReplies(reviewNo);
			
		return replyList;
	}
	
	// 리뷰댓글삭제 (확인완료)
	@GetMapping(value="/{reviewNo}/replies/{replyNo}/deletion")
	public List<ReplyNickname> removeReply(@PathVariable int reviewNo,
			@PathVariable int replyNo, HttpServletRequest request){
	
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {
			
			List<ReplyNickname> replyList = rpservice.removeReply(reviewNo, replyNo);
			
			return replyList;
			
		} else {
			
			List<ReplyNickname> replyList = rpservice.getReplies(reviewNo);
			
			return replyList;
		}
		
		
	}
}
