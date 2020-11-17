package com.db2020.pj.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db2020.pj.config.cookie.CookieUtil;
import com.db2020.pj.config.jwt.JwtUtil;
import com.db2020.pj.config.redis.RedisUtil;
import com.db2020.pj.entity.Customer;
import com.db2020.pj.entity.LoginDTO;
import com.db2020.pj.exception.custom.CUserExistException;
import com.db2020.pj.exception.custom.CUserNotException;
import com.db2020.pj.model.CommonResult;
import com.db2020.pj.model.Response;
import com.db2020.pj.model.SingleResult;
import com.db2020.pj.service.AuthService;
import com.db2020.pj.service.ResponseService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/v1")
public class SignController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthService authService;
	@Autowired
	private CookieUtil cookieUtil;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private ResponseService responseService;

	@PostMapping("/signup")
	public Response signup(HttpServletRequest req, HttpServletResponse res, @RequestBody Customer user) throws Exception {

		System.out.println(user.toString());
		authService.signUp(user);
		
		return new Response("200", "회원가입을 성공적으로 하였습니다.", null);
	}

	@PostMapping("/signin")
	public Response signin(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, String> loginMap) throws Exception {

		Customer user = authService.loginUser(loginMap);
		final String accesstoken = jwtUtil.generateToken(user);
		final String refreshtoken = jwtUtil.generateRefreshToken(user);
		// 제네릭 선언으로 인한여 accessToken 데이터만 가져오게 함.
		final String storage_accssToken = accesstoken;
		Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, accesstoken);
//		Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshtoken);
		redisUtil.setDataExpire(refreshtoken, user.getUsername(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
		res.addCookie(accessToken);
//		res.addCookie(refreshToken);
		
		res.addHeader("Authorization", accessToken.getValue());
		res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "SET_COOKIE");
		res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:3000");
		System.out.println(accessToken.getValue());
		Collection<String> headers = res.getHeaders(HttpHeaders.SET_COOKIE);
		for(String header : headers) {
			res.setHeader(HttpHeaders.SET_COOKIE, header+"; "+ "SameSite=None;");
		}
		LoginDTO login = new LoginDTO(user.getCustomer_seq(), 
									  user.getCustomer_email(), 
									  user.getCustomer_nm(), 
									  user.getCustomer_tel(), 
									  user.getCustomer_post(), 
									  user.getCustomer_address(), 
									  user.getCustomer_detail_address(), 
									  user.getCustomer_role(),
									  accessToken.getValue()
//									  refreshToken.getValue()
									  );
		
		return new Response("200", "로그인을 성공적으로 하였습니다.", login);
	}
	
	@PostMapping("/logout")
	public Response logout(HttpServletRequest req, HttpServletResponse res) throws Exception {
//		Cookie refresh_jwtToken = cookieUtil.getCookie(req, JwtUtil.REFRESH_TOKEN_NAME);
		String jwt = req.getHeader("Authorization");

		final String access_cookie = jwtUtil.generateToken(null);
		final String refresh_cookie = jwtUtil.generateRefreshToken(null);
		
//		Cookie access_cookie = new Cookie(JwtUtil.ACCESS_TOKEN_NAME, "");
//		Cookie refresh_cookie = new Cookie(JwtUtil.REFRESH_TOKEN_NAME, "");
		
//		access_cookie.setPath("/");
//		access_cookie.setMaxAge(0);
//		refresh_cookie.setPath("/");
//		refresh_cookie.setMaxAge(0);
		
//		res.addCookie(access_cookie);
//		res.addCookie(refresh_cookie);
		
//		String jwt = refresh_jwtToken.getValue();
	    
		if(redisUtil.getData(jwt) != null) {
			redisUtil.deleteData(jwt);
		}
		
		return new Response("200", "로그아웃을 성공적으로 하였습니다.", null);
	}
	
//	@PostMapping("/admin/signup")
//	public CommonResult signupAdmin(HttpServletRequest req, HttpServletResponse res, Emp emp) throws Exception {
//		
//		authService.signUpAdmin(emp);
//		return new CommonResult(200, "회원가입을 성공적으로 완료했습니다.");
//	}

}
