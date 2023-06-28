package com.spring.bittlebittle.food.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.food.dao.FoodDao;
import com.spring.bittlebittle.food.vo.Food;
import com.spring.bittlebittle.food.vo.FoodInfo;
import com.spring.bittlebittle.tag.dao.TagDao;
import com.spring.bittlebittle.tag.vo.FoodTag;
import com.spring.bittlebittle.tag.vo.Tag;

@Service
public class FoodServiceImpl implements FoodService{
 
	@Autowired
	private FoodDao fdao;
	@Autowired
	private TagDao tdao;
	
	
	@Override
	public List<Food> getRelatedFoods(int bottleNo) {
		
		return fdao.selectRelatedFoods(bottleNo);
	}
	
	@Override
	public List<Food> getAllFoods() {
		
		return fdao.selectAllFoods();
	}
	
	@Override
	public Map<String, Object> getFood(int foodNo) {
		
		Food food = fdao.selectOne(foodNo);
		List<Tag> tagListByFood = tdao.selectTagByFood(foodNo);
		
		Map<String, Object> map = new HashMap();
		map.put("food", food);
		map.put("tagListByFood", tagListByFood);
		
		
		return map;
	}
	
	@Override
	@Transactional
	public Map<String, Object> addFood(FoodInfo newFood) {
		
		fdao.insertOne(newFood);
		int foodNo = fdao.selectLastFoodNo();
		
		List<FoodTag> foodTagList = new ArrayList();
		
		for(int tagNo : newFood.getTagNoList()) {
			foodTagList.add(new FoodTag(tagNo, foodNo));
		}
		
		tdao.insertFoodTag(foodTagList);
		
		Food food = fdao.selectOne(foodNo);
		List<Tag> tagListByFood = tdao.selectTagByFood(foodNo);
		
		Map<String, Object> map = new HashMap();
		map.put("food", food);
		map.put("tagListByFood", tagListByFood);
		
		return map;
	}
	
	@Override
	@Transactional
	public Map<String, Object> editFood(FoodInfo editFood) {
		
		fdao.updateOne(editFood);
		int foodNo = editFood.getFoodNo();
		
		tdao.deleteFoodTag(foodNo);
		
		List<FoodTag> foodTagList = new ArrayList();
		
		for(int tagNo : editFood.getTagNoList()) {
			foodTagList.add(new FoodTag(tagNo, foodNo));
		}
		
		tdao.insertFoodTag(foodTagList);
		
		Food food = fdao.selectOne(foodNo);
		List<Tag> tagListByFood = tdao.selectTagByFood(foodNo);
		
		Map<String, Object> map = new HashMap();
		map.put("food", food);
		map.put("tagListByFood", tagListByFood);
		
		return map;
	}
	
	@Override
	@Transactional
	public List<Food> removeFood(int foodNo) {

		tdao.deleteFoodTag(foodNo);
		fdao.deleteOne(foodNo);
		
		return fdao.selectAllFoods();
	}
}
