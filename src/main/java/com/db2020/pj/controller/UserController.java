package com.db2020.pj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db2020.pj.model.Response;

/*
 * 1.마이페이지 화면 이동
 * 2.마이페이지 데이터 수정
 */

@RestController
@RequestMapping(value = "/v1")
public class UserController {

	@GetMapping("/mypage")
	public Response mypage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("1");
		
		return new Response("success", "마이페이지 요청을 성공했습니다", null);
	}
	
	@PostMapping("/mypage")
	public Response mypageUpdate(HttpServletRequest req, HttpServletResponse res) {
		return new Response("success", "회원정보 수정을 성공하였습니다.", null);
	}
}
