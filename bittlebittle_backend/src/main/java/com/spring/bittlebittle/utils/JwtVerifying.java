package com.spring.bittlebittle.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;


public class JwtVerifying {

	// JWT 토큰 검증을 수행하는 메소드
	public boolean verifyToken(String token) {
	  try {
	    // JWT 토큰을 검증한다.
	    Claims claims = Jwts.parser().setSigningKey("mySecretKey").parseClaimsJws(token).getBody();
	    // 검증이 성공하면 true를 반환한다.
	    return true;
	  } catch (SignatureException e) {
	    // 검증이 실패하면 false를 반환한다.
	    return false;
	  }
	}

	
}

/*
위 코드에서는 Jwts.parser() 메소드를 이용하여 JWT 토큰을 검증하고, 
검증이 실패하면 SignatureException이 발생합니다. 
검증이 성공하면 true를 반환하고, 실패하면 false를 반환합니다.
*/