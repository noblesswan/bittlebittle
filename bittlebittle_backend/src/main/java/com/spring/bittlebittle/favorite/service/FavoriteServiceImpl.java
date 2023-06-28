package com.spring.bittlebittle.favorite.service;

import com.spring.bittlebittle.favorite.dao.FavoriteDao;
import com.spring.bittlebittle.favorite.vo.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	 
	@Autowired
	private FavoriteDao dao;
	
	@Override
	@Transactional
	public int addFavorite(Favorite favorite) {

		return dao.insertOne(favorite);
	}
	
	@Override
	public List<Favorite> isFavorite(Favorite favorite) {
		
		return dao.selectList(favorite);
	}
	
	@Override
	@Transactional
	public int removeFavorite(Favorite favorite) {
		
		return dao.deleteOne(favorite);
	}
}
