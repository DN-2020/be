package com.db2020.pj.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public SingleResult<Customer> selectMypage(HttpServletRequest req, HttpServletResponse res) {

        final Cookie jwtToken = cookieUtil.getCookie(req, JwtUtil.ACCESS_TOKEN_NAME);

        String jwt = jwtToken.getValue();
        String email = jwtUtil.getUsername(jwt);

        Customer user = userService.userInfo(email);

        return responseService.getSingleResult(user);
    }

    @PutMapping("/user/info")
    public void putMypage(HttpServletRequest req, HttpServletResponse res, @RequestBody Customer customer) {

    	System.out.println(customer.toString());
        userService.userInfo(customer);
    }

    @DeleteMapping("/user/info")
    public void deleteUser(HttpServletRequest req) {

        final Cookie jwtToken = cookieUtil.getCookie(req, JwtUtil.ACCESS_TOKEN_NAME);

        String jwt = jwtToken.getValue();
        String customer_email = jwtUtil.getUsername(jwt);

        userService.removeUser(customer_email);

    }
}