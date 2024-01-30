package com.nkedu.back.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request,
						 HttpServletResponse response,
						 AuthenticationException authException) throws IOException {
		// ��ȿ�� �ڰ������� �������� �ʰ� �����Ϸ� �� ��, 401 (unauthorized) ��ȯ
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
}