package com.db2020.pj.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.db2020.pj.config.cookie.CookieUtil;
import com.db2020.pj.config.redis.RedisUtil;
import com.db2020.pj.entity.Customer;
import com.db2020.pj.exception.custom.CAuthenticationEntryPointException;
import com.db2020.pj.service.detail.CustomUserDetailsService;
//import com.db2020.pj.service.detail.CustomEmpDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

    	System.out.println("필터 시작");
//        final Cookie jwtToken = cookieUtil.getCookie(httpServletRequest, JwtUtil.ACCESS_TOKEN_NAME);

    	final String jwtToken = httpServletRequest.getHeader("Authorization");
    	
    	System.out.println("JWTtoken : " + jwtToken);
        String username = null;
        String emp_email = null;
        String jwt = null;
        String refreshJwt = null;
        String refreshUname = null;

        try {
            if (jwtToken != null) {
//                jwt = jwtToken.getValue();
//                System.out.println("JWT 토큰 값: " + jwtToken.getValue());
                username = jwtUtil.getUsername(jwtToken);
                emp_email = jwtUtil.getEmp_email(jwtToken);
                System.out.println("USER 이름: " + username);
                System.out.println("Emp 메일: " + emp_email);
            }
            if (username != null) {
                String value = ":1";
                System.out.println("일반 유저일 경우");
                String username1 = username + value;
                UserDetails userDetails = userDetailsService.loadUserByUsername(username1);
                if (jwtUtil.validateToken(value, jwtToken, userDetails)) {
                    System.out.println("AccessToken 통과");
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            if (emp_email != null) {
                String value = ":2";
                System.out.println("회사일 경우");
                String username1 = emp_email + value;
                UserDetails userDetails = userDetailsService.loadUserByUsername(username1);
                if (jwtUtil.validateToken(value, jwtToken, userDetails)) {
                    System.out.println("AccessToken 통과");
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (ExpiredJwtException e) {
        	System.out.println("AcessToken 만료");
//            Cookie refreshToken = cookieUtil.getCookie(httpServletRequest, JwtUtil.REFRESH_TOKEN_NAME);
//            if (refreshToken != null) {
//                refreshJwt = refreshToken.getValue();
//            }
        } catch (Exception e) {

        }
//        try {
//            if (refreshJwt != null) {
//                refreshUname = redisUtil.getData(refreshJwt);
//                System.out.println("1" + refreshUname);
//                if (refreshUname.equals(jwtUtil.getUsername(refreshJwt))) {
//                    UserDetails userDetails = userDetailsService.loadUserByUsername(refreshUname);
//                    System.out.println("2" + userDetails.getUsername());
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    usernamePasswordAuthenticationToken
//                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//                    Customer user = new Customer();
//                    user.setCustomer_email(refreshUname);
//                    String newToken = jwtUtil.generateToken(user);
//
//                    Cookie newAccessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, newToken);
//                    httpServletResponse.addCookie(newAccessToken);
//                }
//            }
//        } catch (ExpiredJwtException e) {
//            System.out.println("Refresh 만료");
//        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}