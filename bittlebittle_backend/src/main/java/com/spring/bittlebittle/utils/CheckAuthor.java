package com.spring.bittlebittle.utils;

import java.util.List;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;


public class CheckAuthor {
	
	
	public boolean checkAuthorization(String token, String url) {
		  try {
		    // JWT ��ū�� �����Ѵ�.
		    Claims claims = Jwts.parser().setSigningKey("mySecretKey").parseClaimsJws(token).getBody();
		    // ���� ������ ��ȸ�Ѵ�.
		    List<String> authorities = claims.get("authorities", List.class);
		    // ��û URL�� ���� ������ �ִ��� Ȯ���Ѵ�.
		    return authorities.contains(url);
		  } catch (SignatureException e) {
		    // ������ �����ϸ� ������ ���� ������ ó���Ѵ�.
		    return false;
		  }
		}

	

}

/*
�� �ڵ忡���� ������ JWT ��ū���� ���� ������ ��ȸ�ϰ�, ��û URL�� ���� ������ �ִ��� Ȯ���մϴ�. 
�̸� �̿��Ͽ�, ������ ���Ѻο��� ������ �� �ֽ��ϴ�.
*/