package com.spring.bittlebittle.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;


public class JwtVerifying {

	// JWT ��ū ������ �����ϴ� �޼ҵ�
	public boolean verifyToken(String token) {
	  try {
	    // JWT ��ū�� �����Ѵ�.
	    Claims claims = Jwts.parser().setSigningKey("mySecretKey").parseClaimsJws(token).getBody();
	    // ������ �����ϸ� true�� ��ȯ�Ѵ�.
	    return true;
	  } catch (SignatureException e) {
	    // ������ �����ϸ� false�� ��ȯ�Ѵ�.
	    return false;
	  }
	}

	
}

/*
�� �ڵ忡���� Jwts.parser() �޼ҵ带 �̿��Ͽ� JWT ��ū�� �����ϰ�, 
������ �����ϸ� SignatureException�� �߻��մϴ�. 
������ �����ϸ� true�� ��ȯ�ϰ�, �����ϸ� false�� ��ȯ�մϴ�.
*/