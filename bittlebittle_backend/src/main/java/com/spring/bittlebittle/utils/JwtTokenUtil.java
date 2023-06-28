package com.spring.bittlebittle.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.bittlebittle.user.dao.UserDao;
import com.spring.bittlebittle.user.vo.User;



public class JwtTokenUtil  {

	@Autowired
    private SqlSession sqlSession;
	
//	@Autowired
//    private UserDao userDao;
	
	
	// 로그인 인증을 수행하는 메소드
	public String authenticateUser(String userId, String userPwd) {
	  // DB에서 사용자 정보를 조회한다.
//	  User user1 = sqlSession.selectOne("getUserByCredentials", Map.of("user_name", userName, "user_pwd", userPwd));
		
		Map<String, String> credentials = new HashMap<>();
		credentials.put("user_id", userId);
		credentials.put("user_pwd", userPwd);
		User user1 = sqlSession.selectOne("getUserByCredentials", credentials);
	  // 사용자 정보가 없으면 로그인 실패
	  if (user1 == null) {
	    return null;
	  }
	  // JWT 토큰을 생성한다.
	  String token = Jwts.builder()
	      .setSubject(user1.getUserName())
	      .setIssuedAt(new Date())
	      .setExpiration(new Date(System.currentTimeMillis() + 86400000))
	      .signWith(SignatureAlgorithm.HS512, "mySecretKey")
	      .compact();
	  return token;
	}


}