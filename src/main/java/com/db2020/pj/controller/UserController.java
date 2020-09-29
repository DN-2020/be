package com.db2020.pj.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db2020.pj.config.cookie.CookieUtil;
import com.db2020.pj.config.jwt.JwtUtil;
import com.db2020.pj.entity.Customer;
import com.db2020.pj.model.ListResult;
import com.db2020.pj.model.Response;
import com.db2020.pj.service.ResponseService;

/*
 * 1.마이페이지 화면 이동
 * 2.마이페이지 데이터 수정
 */
@RestController
@RequestMapping(value = "/v1")
public class UserController {
	
//	@Autowired
//	private CookieUtil cookieUtil;
//	@Autowired
//	private ResponseService responseService;

//	@GetMapping("/user/info")
//	public ListResult<User> mypage(HttpServletRequest req, HttpServletResponse res) {
//
////		final Cookie jwtToken = cookieUtil.getCookie(req, JwtUtil.ACCESS_TOKEN_NAME);
////		
////		String id = jwtToken.getValue();
////		System.out.println(id);
//		
//		return responseService.getListResult(null);
//	}
	
	@PostMapping("/mypage")
	public Response mypageUpdate(HttpServletRequest req, HttpServletResponse res) {
		return new Response("success", "회원정보 수정을 성공하였습니다.", null);
	}
}