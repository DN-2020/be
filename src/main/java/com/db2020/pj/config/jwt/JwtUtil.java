package com.db2020.pj.config.jwt;

import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;

import com.db2020.pj.entity.Emp;
import com.db2020.pj.entity.EmpDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.db2020.pj.entity.Customer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

    //    public final static long TOKEN_VALIDATION_SECOND = 1000 * 60L * 60L * 2L ;  		// Access_Token  2시간 설정
    public final static long TOKEN_VALIDATION_SECOND = 1000 * 60L * 60L * 48L ;  		// Access_Token  2일 설정
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000 * 60L * 60L * 24L;   // Refresh_Token 하루 설정

    final static public String ACCESS_TOKEN_NAME = "accessToken";
    final static public String REFRESH_TOKEN_NAME = "refreshToken";

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;


//    @PostConstruct
//	protected void init() {
//
//		Random rnd = new Random();
//		StringBuilder temp = new StringBuilder();
//		for (int i = 0; i < 30; i++) {
//			int rIndex = rnd.nextInt(3);
//			switch (rIndex) {
//			case 0:
//				// a-z
//				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
//				break;
//			case 1:
//				// A-Z
//				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
//				break;
//			case 2:
//				// 0-9
//				temp.append((rnd.nextInt(10)));
//				break;
//			}
//		}
//		SECRET_KEY = temp.toString();
//		SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
//	}
//    private Key getSigningKey(String secretKey) {
//        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    // 토큰이 유효한 토큰인지 검사한 후, 토큰에 담긴 Payload 값을 가져온다
    public Claims extractAllClaims(String token) {
        System.out.println("extractAllClaims method ::" + token);
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }


    // 추출한 Payload로 부터 userName을 가져온다.
    public String getUsername(String token) {
        System.out.println("getUserName method ::"+ token);
        return extractAllClaims(token).get("username", String.class);
    }

    public String getEmp_email(String token) {
        System.out.println("getCompany_seq method ::"+ token);
        return extractAllClaims(token).get("emp_email", String.class);
    }

    // 토큰의 만료를 확인
    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    // Access Token을 형성
    public String generateToken(Customer user) {
        return doGenerateToken(user.getUsername(), TOKEN_VALIDATION_SECOND);
    }

    // Refresh Token을 형성
    public String generateRefreshToken(Customer user) {
        return doGenerateToken(user.getUsername(), REFRESH_TOKEN_VALIDATION_SECOND);
    }

    // Access Token을 형성
    public String generateEmpToken(Emp emp) {
        return doGenerateToken1(emp.getEmp_email(), emp.getCompany_seq(), TOKEN_VALIDATION_SECOND);
    }

    // Refresh Token을 형성
    public String generateRefreshEmpToken(Emp emp) {
        return doGenerateToken1(emp.getEmp_email(), emp.getCompany_seq(), REFRESH_TOKEN_VALIDATION_SECOND);
    }

    // 토큰을 생성, 페이로드에 담길 값을 username에 담는다
    public String doGenerateToken(String username, long expireTime) {

        Claims claims = Jwts.claims();
        claims.put("username", username);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return jwt;
    }

    // 토큰을 생성, 페이로드에 담길 값을 username에 담는다
    public String doGenerateToken1(String emp_email, int company_seq, long expireTime) {

        System.out.println(emp_email);
        Claims claims = Jwts.claims();
        claims.put("emp_email", emp_email);
        claims.put("company_seq", company_seq);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return jwt;
    }

    public Boolean validateToken(String value, String token, UserDetails userDetails) {
        if(value.equals(":1")){
            final String username = getUsername(token);
            System.out.println(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }
        else {
            final String username = getEmp_email(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }
    }
}