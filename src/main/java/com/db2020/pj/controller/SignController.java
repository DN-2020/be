package com.db2020.pj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
	public CommonResult signup(HttpServletRequest req, HttpServletResponse res, @RequestBody Customer user) throws Exception {

		authService.signUp(user);
		return new CommonResult(200, "회원가입을 성공적으로 완료했습니다.");

	}

	@PostMapping("/signin")
	public SingleResult<LoginDTO> signin(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, String> loginMap) throws Exception {

		Customer user = authService.loginUser(loginMap);
		final String accesstoken = jwtUtil.generateToken(user);
		final String refreshtoken = jwtUtil.generateRefreshToken(user);
		// 제네릭 선언으로 인한여 accessToken 데이터만 가져오게 함.
		final String storage_accssToken = accesstoken;
		Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, accesstoken);
		Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshtoken);
		redisUtil.setDataExpire(refreshtoken, user.getUsername(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
		res.addCookie(accessToken);
		res.addCookie(refreshToken);
		
		LoginDTO login = new LoginDTO(user.getCustomer_seq(), user.getCustomer_email(), user.getCustomer_nm(), user.getCustomer_tel(), user.getCustomer_post(), user.getCustomer_address(), user.getCustomer_detail_address(), accesstoken , user.getCustomer_role());
		
		return responseService.getSingleResult(login);
	}
	
	@PostMapping("/logout")
	public CommonResult logout(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Cookie refresh_jwtToken = cookieUtil.getCookie(req, JwtUtil.REFRESH_TOKEN_NAME);

		Cookie access_cookie = new Cookie(JwtUtil.ACCESS_TOKEN_NAME, "");
		Cookie refresh_cookie = new Cookie(JwtUtil.REFRESH_TOKEN_NAME, "");
		
		access_cookie.setPath("/");
		access_cookie.setMaxAge(0);
		refresh_cookie.setPath("/");
		refresh_cookie.setMaxAge(0);
		
		res.addCookie(access_cookie);
		res.addCookie(refresh_cookie);
		
		String jwt = refresh_jwtToken.getValue();
	    
		if(redisUtil.getData(jwt) != null){
			redisUtil.deleteData(jwt);
		}
		
		return new CommonResult(200, "로그아웃을 성공적으로 완료했습니다.");
	}
	
//	@PostMapping("/admin/signup")
//	public CommonResult signupAdmin(HttpServletRequest req, HttpServletResponse res, Emp emp) throws Exception {
//		
//		authService.signUpAdmin(emp);
//		return new CommonResult(200, "회원가입을 성공적으로 완료했습니다.");
//	}

}
