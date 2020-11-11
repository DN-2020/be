package com.db2020.pj.controller;

import com.db2020.pj.config.cookie.CookieUtil;
import com.db2020.pj.config.jwt.JwtUtil;
import com.db2020.pj.config.redis.RedisUtil;
import com.db2020.pj.model.Response;
import com.db2020.pj.service.AuthService;
import com.db2020.pj.service.ResponseService;
import com.db2020.pj.service.ReviewService;
import com.db2020.pj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;


@RestController
@CrossOrigin("*") 
@RequestMapping(value = "/v1")
public class reviewController {

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
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;


    //리뷰 등록
    @PostMapping("/review")
    public Response addReview(@RequestBody HashMap<String, Object> map,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);

        String jwt = jwtToken.getValue();
        String email = jwtUtil.getUsername(jwt);
        map.put("customer_id", email);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);
        reviewService.insertReview(map);

        return new Response("200", "리뷰 등록을 성공했습니다.", null);
    }

    //회사 리뷰 조회
    @GetMapping("/compnay/{company_seq}/review")
    public Response companyReview(@PathVariable Integer company_seq){
        HashMap<String ,Object> map = new HashMap<>();
        map.put("compnay_seq", company_seq);

        List<HashMap<String, Object>> result = reviewService.selectCompanyReviewList(map);
        if(result.isEmpty()){
            result = null;
        }
        return new Response("200", "회사 리뷰 조회를 성공하셨습니다." ,result);
    }
    //상품 리뷰 조회

    @GetMapping("/goods/{goods_seq}/review")
    public Response goodsReview(@PathVariable Integer goods_seq){
        HashMap<String ,Object> map = new HashMap<>();
        map.put("goods_seq", goods_seq);

        List<HashMap<String, Object>> result = reviewService.selectGoodsReviewList(map);
        if(result.isEmpty()){
            result = null;
        }
        return new Response("200", "상품 리뷰 조회를 성공하셨습니다." ,result);
    }
    //상품 상세 리뷰 조회
    @GetMapping("/goodsdetail/{goods_detail_seq}/review")
    public Response goodsDetailReview(@PathVariable Integer goods_detail_seq){
        HashMap<String ,Object> map = new HashMap<>();
        map.put("goods_detail_seq", goods_detail_seq);
        List<HashMap<String, Object>> result = reviewService.selectGoodsDetailReviewList(map);
        if(result.isEmpty()){
            result = null;
        }
        return new Response("200", "상세 상품 리뷰 조회를 성공하셨습니다." ,result);
    }
    // 리뷰 리스트 조회 (상품별 및 회사별)
    @GetMapping("/review/list")
    public Response getReview(@RequestBody HashMap<String, Object> map,
                              HttpServletRequest request,
                              HttpServletResponse response) {

        List<HashMap<String, Object>> result = reviewService.selectListReview(map);
        if(result.isEmpty()){
            result = null;
        }
        return new Response("200", "리뷰 리스트 조회(상품별 및 회사별) 성공하셨습니다.", result);
    }

    // 후기 리스트 조회
    @GetMapping("user/review/list")
    public Response getUserReview(HttpServletRequest request,
                                  HttpServletResponse response) {
        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);
        HashMap<String, Object> map = new HashMap<>();
        String jwt = jwtToken.getValue();
        String email = jwtUtil.getUsername(jwt);
        map.put("customer_id", email);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);
        List<HashMap<String, Object>> result = reviewService.selectUserReview(map);
        if(result.isEmpty()){
            result = null;
        }
        return new Response("200", "사용자 후기 리스트 조회 성공하였습니다.", result);
    }

    // 고객별 후기 조회
    @GetMapping("review/{review_seq}")
    public Response getDetailReview(@PathVariable int review_seq,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
    	HashMap<String, Object> map = new HashMap<>();
        map.put("review_seq", review_seq);

        HashMap<String, Object> result = reviewService.selectDetailReview(map);
        return new Response("200", "사용자 상세 후기 조회 성공하셨습니다.", result);
    }

    @PutMapping("review/{review_seq}")
    public Response updateDetailReview(@RequestBody HashMap<String, Object> map,
                                       @PathVariable int review_seq,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);

        String jwt = jwtToken.getValue();
        String email = jwtUtil.getUsername(jwt);
        map.put("customer_id", email);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);
        map.put("review_seq", review_seq);
        reviewService.updateReview(map);
        return new Response("200", "리뷰 변경을 성공 하셨습니다.", null);
    }

}
