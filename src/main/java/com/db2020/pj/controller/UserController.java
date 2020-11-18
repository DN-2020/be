package com.db2020.pj.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db2020.pj.config.cookie.CookieUtil;
import com.db2020.pj.config.jwt.JwtUtil;
import com.db2020.pj.entity.Customer;
import com.db2020.pj.model.CommonResult;
import com.db2020.pj.model.ListResult;
import com.db2020.pj.model.Response;
import com.db2020.pj.model.SingleResult;
import com.db2020.pj.service.AuthService;
import com.db2020.pj.service.ResponseService;
import com.db2020.pj.service.UserService;

/*
 * 1.마이페이지 화면 이동
 * 2.마이페이지 데이터 수정
 */
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/v1")
public class UserController {

    @Autowired
    private CookieUtil cookieUtil;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @GetMapping("/user/info")
    public Response selectMypage(HttpServletRequest req, HttpServletResponse res) {

    	System.out.println("토큰 전송 확인");
//        final Cookie jwtToken = cookieUtil.getCookie(req, JwtUtil.ACCESS_TOKEN_NAME);
    	String jwt = req.getHeader("Authorization");
    	System.out.println(req.getHeader("Authorization"));
//        System.out.println("쿠키 여부 확인");
//        String jwt = jwtToken.getValue();
//        System.out.println("3" + jwt);
        String email = jwtUtil.getUsername(jwt);

        Customer user = userService.userInfo(email);

        return new Response("200", "유저정보를 성공적으로 조회하였습니다.", user);
    }

    @PutMapping("/user/info")
    public Response putMypage(HttpServletRequest req, HttpServletResponse res, @RequestBody Customer customer) {

        String jwt = req.getHeader("Authorization");

        String customer_email = jwtUtil.getUsername(jwt);

        customer.setCustomer_email(customer_email);

        userService.userInfo(customer);
        
        return new Response("200", "유저정보를 성공적으로 수정하였습니다.", null);
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.DELETE)
    public Response deleteUser(HttpServletRequest req) {

//        final Cookie jwtToken = cookieUtil.getCookie(req, JwtUtil.ACCESS_TOKEN_NAME);
    	String jwt = req.getHeader("Authorization");
    	System.out.println(req.getHeader("Authorization"));
    	
//        String jwt = jwtToken.getValue();
        String customer_email = jwtUtil.getUsername(jwt);

        userService.removeUser(customer_email);

        return new Response("200", "유저정보를 성공적으로 삭제하였습니다.", null);
    }
}