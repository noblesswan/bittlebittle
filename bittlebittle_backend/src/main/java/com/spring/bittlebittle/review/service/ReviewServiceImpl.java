package com.spring.bittlebittle.review.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.reply.dao.ReplyDao;
import com.spring.bittlebittle.reply.vo.ReplyNickname;
import com.spring.bittlebittle.review.dao.ReviewDao;
import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.review.vo.ReviewNickname;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDao rdao;
	@Autowired
	private ReplyDao rpdao;
	
	@Override
	public List<ReviewNickname> getReviews(int bottleNo) {
		
		return rdao.selectList(bottleNo);
	}
	
	@Override
	@Transactional
	public List<ReviewNickname> addReview(Review review) {
		
		rdao.insertOne(review);
	
		
		List<ReviewNickname> list = rdao.selectList(review.getBottleNo());
		
		return list;
	}
	
	@Override
	public Map<String, Object> getReview(int reviewNo) {
		
		ReviewNickname review = rdao.selectOne(reviewNo);
		List<ReplyNickname> replyList= rpdao.selectList(reviewNo);
				
		Map<String, Object> map = new HashMap<>();
		map.put("review", review);
		map.put("replyList", replyList);
		
		return map;
	}
	
	@Override
	@Transactional
	public List<ReviewNickname> editReview(Review review) {
		
		rdao.updateOne(review);
		
		List<ReviewNickname> list = rdao.selectList(review.getBottleNo());
		
		return list;
	}
	
	@Override
	@Transactional
	public List<ReviewNickname> removeReview(int bottleNo, int reviewNo) {

		rdao.deleteOne(reviewNo);
		rpdao.deleteByReview(reviewNo);
		List<ReviewNickname> list = rdao.selectList(bottleNo);
		
		return list;
	}
}
