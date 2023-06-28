package com.spring.bittlebittle.review.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.review.vo.ReviewNickname;

public interface ReviewDao {

	List<ReviewNickname> selectList(int bottleNo);

	ReviewNickname selectOne(int reviewNo);

	int insertOne(Review review);

	int updateOne(Review review);

	int deleteOne(int reviewNo);

	int selectGradeByBottle(int bottleNo);


}
