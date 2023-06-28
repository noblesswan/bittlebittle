package com.spring.bittlebittle.review.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.review.vo.ReviewNickname;

@Repository
public class ReviewDaoImpl implements ReviewDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ReviewNickname> selectList(int bottleNo) {
		
		return sqlSession.selectList("reviewMapper.selectList", bottleNo);
	}
	
	@Override
	public ReviewNickname selectOne(int reviewNo) {
		
		return sqlSession.selectOne("reviewMapper.selectOne", reviewNo);
	}
	
	@Override
	public int insertOne(Review review) {
		
		return sqlSession.insert("reviewMapper.insertOne", review);
		
	}
	
	@Override
	public int updateOne(Review review) {
		
		return sqlSession.update("reviewMapper.updateOne", review);
	}
	
	@Override
	public int deleteOne(int reviewNo) {
		
		return sqlSession.delete("reviewMapper.deleteOne", reviewNo);
	}
	
	@Override
	public int selectGradeByBottle(int bottleNo) {
		
		return sqlSession.selectOne("reviewMapper.selectOneGrade", bottleNo);
	}
	
}
