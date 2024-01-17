package com.nkedu.back.security;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.io.Decoders;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtTokenizerTest {
	private static JwtTokenizer jwtTokenizer;
	private String secretKey;
	private String base64EncodedSecretKey;

	@BeforeAll
	public void init() {
		jwtTokenizer = new JwtTokenizer();
		secretKey = "testsecretkeytestsecretkeytestsecretkey";
		
		base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(secretKey);
	}
	
	@Test
	public void encodeBase64SecretKeyTest() {
		System.out.println(base64EncodedSecretKey);
		
		assertThat(secretKey, is(new String(Decoders.BASE64.decode(base64EncodedSecretKey))));
	}
	
	@Test
	public void generateAccessTokenTest() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id",  1);
		claims.put("roles", List.of("USER"));
		
		String subject = "test access token";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 10);
		Date expiration = calendar.getTime();
		
		String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);
		
		System.out.println(accessToken);
		
		assertThat(accessToken, notNullValue());
	}
	
	@Test
	public void generateRefreshTokenTest() {
		String subject = "test refresh token";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 10);
		Date expiration = calendar.getTime();
		
		String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);
		
		System.out.println(refreshToken);
		
		assertThat(refreshToken, notNullValue());
	}
}
