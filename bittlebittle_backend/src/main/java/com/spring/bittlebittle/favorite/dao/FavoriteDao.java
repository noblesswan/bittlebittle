package com.spring.bittlebittle.favorite.dao;

import com.spring.bittlebittle.favorite.vo.Favorite;

import java.util.List;

public interface FavoriteDao {

	int insertOne(Favorite favorite);

	List<Favorite> selectList(Favorite favorite);

	int deleteOne(Favorite favorite);

}
