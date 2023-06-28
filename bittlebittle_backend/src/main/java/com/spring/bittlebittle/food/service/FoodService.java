package com.spring.bittlebittle.food.service;

import java.util.List;
import java.util.Map;

import com.spring.bittlebittle.food.vo.Food;
import com.spring.bittlebittle.food.vo.FoodInfo;

public interface FoodService {

	List<Food> getRelatedFoods(int bottleNo);

	List<Food> getAllFoods();

	Map<String, Object> addFood(FoodInfo newFood);

	Map<String, Object> getFood(int foodNo);

	Map<String, Object> editFood(FoodInfo editFood);

	List<Food> removeFood(int foodNo);

}
