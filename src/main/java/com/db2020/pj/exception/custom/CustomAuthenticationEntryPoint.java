package com.db2020.pj.exception.custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

// 온전한 JWT가 전달이 안될 경우는 토큰 인증 처리 자체가 불가능하기 때문에, 토큰 검증 단에서 프로세스가 끝나게 된다. (즉, spring으로 들어오지 않아 예외처리가 불가능)
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		
		System.out.println("예외처리");

		response.sendRedirect("/exception/entrypoint");
		
	}
}