package com.db2020.pj.config.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.db2020.pj.config.jwt.JwtUtil;

@Service
public class CookieUtil {

	public Cookie createCookie(String cookieName, String value) {
		Cookie token = new Cookie(cookieName, value);
		token.setHttpOnly(true);
		token.setMaxAge((int) JwtUtil.TOKEN_VALIDATION_SECOND);
		token.setPath("/");
		return token;
	}
	
	public Cookie getCookie(HttpServletRequest req, String cookieName){
		System.out.println(req.getProtocol());
		System.out.println(req.getRequestURL());
		
		System.out.println("AccessToken: " + req.getHeader("accessToken"));
		System.out.println("RefreshToken: " + req.getHeader("refreshToken"));
		System.out.println("전송 결과: " + req.toString());
		System.out.println("쿠키 이름: " + cookieName);
        final Cookie[] cookies = req.getCookies();
        if(cookies==null) {
        	System.out.println("쿠키값이 없음");
        	return null;
        }
        for(Cookie cookie : cookies){
        	System.out.println("쿠키값이 오긴 왔음");
            if(cookie.getName().equals(cookieName))
                return cookie;
        }
        return null;
    }
}
