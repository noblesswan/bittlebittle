package com.spring.bittlebittle.tag.service;

import com.spring.bittlebittle.tag.dao.TagDao;
import com.spring.bittlebittle.tag.vo.BottleTag;
import com.spring.bittlebittle.tag.vo.FoodTag;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	private TagDao dao;
	
	@Override
	public List<TagType> getAllTagTypes() {
		
		return dao.selectAllTagType();
	}
	
	@Override
	public List<Tag> getAllTags() {
		
		return dao.selectAllTag();
	}
	
	@Override
	public List<Tag> getTagsByBottle(int bottleNo) {
		
		return dao.selectTagByBottle(bottleNo);
	}
	
	@Override
	@Transactional
	public void editBottleTag(int bottleNo, List<BottleTag> tagList) {
		
		dao.deleteBottleTag(bottleNo);
		dao.insertBottleTag(tagList);
		
	}
	@Override
	@Transactional
	public void addBottleTag(List<BottleTag> tagList) {
		dao.insertBottleTag(tagList);
		
	}
	
	@Override
	@Transactional
	public void removeBottleTag(int bottleNo) {
		
		dao.deleteBottleTag(bottleNo);
		
	}
	
	@Override
	@Transactional
	public List<TagType> addTagType(String tagTypeName) {
		dao.insertTagType(tagTypeName);
		
		return dao.selectAllTagType();
	}
	
	@Override
	@Transactional
	public List<TagType> editTagType(TagType tagType) {
		
		dao.updateTagType(tagType);
		
		return dao.selectAllTagType();
	}
	
	@Override
	@Transactional
	public List<TagType> removeTagType(int tagTypeNo) {

		dao.deleteTagType(tagTypeNo);
		
		return dao.selectAllTagType();
	}
	
	@Override
	@Transactional
	public List<Tag> addTag(Tag tag) {
		
		dao.insertTag(tag);

		return dao.selectAllTag();
	}

	@Override
	@Transactional
	public List<Tag> editTag(Tag tag) {
		
		dao.updateTag(tag);
		
		return dao.selectAllTag();
	}
	
	@Override
	@Transactional
	public List<Tag> removeTag(int tagNo) {

		dao.deleteTag(tagNo);
		return dao.selectAllTag();
	}
	
	@Override
	@Transactional
	public void addFoodTag(List<FoodTag> foodTagList) {
		
		dao.insertFoodTag(foodTagList);
		
	}
	
	@Override
	public List<Tag> getTagsByFood(int foodNo) {
		
		return dao.selectTagByFood(foodNo);
	}

	@Override
	@Transactional
	public void editFoodTag(int foodNo, List<FoodTag> foodTagList) {

		dao.deleteFoodTag(foodNo);
		dao.insertFoodTag(foodTagList);
		
	}
	
	@Override
	@Transactional
	public void removeFoodTag(int foodNo) {
		
		dao.deleteFoodTag(foodNo);
		
	}

	@Override
	public Map<String, Object> getTags() {

		List<Tag> tagList = dao.selectAllTag();
		List<TagType> tagTypeList = dao.selectAllTagType();

		Map<String, Object> map = new HashMap<>();
		map.put("tagList", tagList);
		map.put("tagTypeList", tagTypeList);

		return map;
	}

}
