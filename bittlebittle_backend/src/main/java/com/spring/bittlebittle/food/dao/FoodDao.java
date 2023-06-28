package com.spring.bittlebittle.food.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.food.vo.Food;
import com.spring.bittlebittle.food.vo.FoodInfo;

public interface FoodDao {

	List<Food> selectRelatedFoods(int bottleNo);

	List<Food> selectAllFoods();

	void insertOne(FoodInfo newFood);

	Food selectOne(int foodNo);

	void updateOne(FoodInfo editFood);

	void deleteOne(int foodNo);

	int selectLastFoodNo();

}
