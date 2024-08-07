package com.example.todo.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret-key}")
	private String secretKey;
	
	@Value("${jwt.expired}")
	private Long expiredMs;
	
	private static String staticSecretKey;
	private static Long staticExpiredMs;
	
	@PostConstruct
	public void init() {
		staticSecretKey = this.secretKey;
		staticExpiredMs = this.expiredMs;
	}
	
	public static String generateToken(String userId) {
		
		Claims claim = Jwts.claims();
		claim.put("userId", userId);
		
		Key key = Keys.hmacShaKeyFor(staticSecretKey.getBytes(StandardCharsets.UTF_8));
		
		return Jwts.builder()
				.setClaims(claim)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + staticExpiredMs))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	public static boolean isExpired(String token) throws ExpiredJwtException {
		return Jwts.parserBuilder()
				.setSigningKey(staticSecretKey.getBytes(StandardCharsets.UTF_8))
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getExpiration()	//만료시간
				.before(new Date());//만료시간이 현재 보다 이전이냐 ? true (만료) : false (유효) 
	}
	
	public static String getUserId(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(staticSecretKey.getBytes(StandardCharsets.UTF_8))
				.build()
				.parseClaimsJws(token)
				.getBody()
				.get("userId", String.class);
	}	
}
