package com.spring.bittlebittle.tag.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.tag.vo.BottleTag;
import com.spring.bittlebittle.tag.vo.FoodTag;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;

@Repository
public class TagDaoImpl implements TagDao{

	@Autowired
	private SqlSession sqlSession;
	
	Logger log = LogManager.getLogger("case3");
	
	@Override
	public List<TagType> selectAllTagType() {
		
		
		return sqlSession.selectList("tagMapper.selectAllTagType");
	}
	
	@Override
	public List<Tag> selectAllTag() {
		
		return sqlSession.selectList("tagMapper.selectAllTag");
	}
	
	@Override
	public List<Tag> selectTagByBottle(int bottleNo) {
		
		return sqlSession.selectList("tagMapper.selectTagByBottle", bottleNo);
	}
	
	@Override
	public void deleteBottleTag(int bottleNo) {
		
		sqlSession.delete("tagMapper.deleteBottleTag", bottleNo);
	}
	
	@Override
	public void insertBottleTag(List<BottleTag> bottleTagList) {
		
		sqlSession.delete("tagMapper.insertBottleTag", bottleTagList);
		
	}
	
	@Override
	public void insertTagType(String tagTypeName) {
		
		sqlSession.insert("tagMapper.insertTagType", tagTypeName);
	}
	
	@Override
	public void updateTagType(TagType tagType) {
		
		sqlSession.update("tagMapper.updateTagType", tagType);
		
	}
	@Override
	public void deleteTagType(int tagTypeNo) {
		
		sqlSession.delete("tagMapper.deleteTagType", tagTypeNo);
		
	}
	
	@Override
	public void insertTag(Tag tag) {

		sqlSession.insert("tagMapper.insertTag", tag);
		
	}
	
	@Override
	public void updateTag(Tag tag) {
		
		sqlSession.update("tagMapper.updateTag", tag);
	}
	
	@Override
	public void deleteTag(int tagNo) {
		
		sqlSession.delete("tagMapper.deleteTag", tagNo);
	} 
	
	@Override
	public void insertFoodTag(List<FoodTag> foodTagList) {
		
		sqlSession.insert("tagMapper.insertFoodTag", foodTagList);
	}
	
	@Override
	public List<Tag> selectTagByFood(int foodNo) {
		
		return sqlSession.selectList("tagMapper.selectTagByFood", foodNo);
	}
	
	@Override
	public void deleteFoodTag(int foodNo) {
		
		sqlSession.delete("tagMapper.deleteFoodTag", foodNo);
		
	}

}
