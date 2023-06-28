package com.spring.bittlebittle.bottle.dao;


import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleAll;
import com.spring.bittlebittle.bottle.vo.BottleInfo;
import com.spring.bittlebittle.bottle.vo.BottleSearch;
import java.util.List;


public interface BottleDao {

	List<BottleAll> selectAllBottles(BottleSearch bottleSearch);
	
//	List<Bottle> selectMainList();

	List<Bottle> selectNewList(int userNo);

	List<Bottle> selectBestList(int userNo);

	List<Bottle> selectRelatedFavoriteList(int userNo);

	Bottle selectOne(int bottleNo);

	List<Bottle> selectRelatedBottleList(int bottleNo);

	int updateOne(BottleInfo editBottle);

	int insertOne(BottleInfo bottle);

	void editViewCnt(int bottleNo);
	
	void deleteOne(int bottleNo);

	int selectLastBottleNo();
	


}
