package com.spring.bittlebittle.favorite.service;

import java.util.List;

import com.spring.bittlebittle.favorite.vo.Favorite;

import java.util.List;

public interface FavoriteService {

	int addFavorite(Favorite favorite);

	List<Favorite> isFavorite(Favorite favorite);

	int removeFavorite(Favorite favorite);

}
