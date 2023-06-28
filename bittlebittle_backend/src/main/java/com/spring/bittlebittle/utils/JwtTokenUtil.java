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
	
	
	// �α��� ������ �����ϴ� �޼ҵ�
	public String authenticateUser(String userId, String userPwd) {
	  // DB���� ����� ������ ��ȸ�Ѵ�.
//	  User user1 = sqlSession.selectOne("getUserByCredentials", Map.of("user_name", userName, "user_pwd", userPwd));
		
		Map<String, String> credentials = new HashMap<>();
		credentials.put("user_id", userId);
		credentials.put("user_pwd", userPwd);
		User user1 = sqlSession.selectOne("getUserByCredentials", credentials);
	  // ����� ������ ������ �α��� ����
	  if (user1 == null) {
	    return null;
	  }
	  // JWT ��ū�� �����Ѵ�.
	  String token = Jwts.builder()
	      .setSubject(user1.getUserName())
	      .setIssuedAt(new Date())
	      .setExpiration(new Date(System.currentTimeMillis() + 86400000))
	      .signWith(SignatureAlgorithm.HS512, "mySecretKey")
	      .compact();
	  return token;
	}


}