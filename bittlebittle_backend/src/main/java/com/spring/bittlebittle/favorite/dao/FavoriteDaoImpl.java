package com.spring.bittlebittle.favorite.dao;

import com.spring.bittlebittle.favorite.vo.Favorite;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteDaoImpl implements FavoriteDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertOne(Favorite favorite) {

		return sqlSession.insert("favoriteMapper.insertOne", favorite);

	}
	
	@Override
	public List<Favorite> selectList(Favorite favorite) {
		
		return sqlSession.selectList("favoriteMapper.selectOne", favorite);
	}
	
	
	@Override
	public int deleteOne(Favorite favorite) {
		
		return sqlSession.delete("favoriteMapper.deleteOne", favorite);
	}
}
