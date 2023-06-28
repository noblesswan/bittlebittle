package com.spring.bittlebittle.review.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.review.vo.ReviewNickname;

public interface ReviewService {

	List<ReviewNickname> getReviews(int bottleNo);

	Map<String, Object> getReview(int reviewNo);
	
	List<ReviewNickname> addReview(Review review);

	List<ReviewNickname> editReview(Review review);

	List<ReviewNickname> removeReview(int bottleNo, int reviewNo);

}
