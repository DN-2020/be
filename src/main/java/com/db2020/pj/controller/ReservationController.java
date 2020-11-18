package com.db2020.pj.controller;

import com.db2020.pj.config.jwt.JwtUtil;
import com.db2020.pj.model.Response;
import com.db2020.pj.service.ReservationService;
import com.db2020.pj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/v1")
public class ReservationController {

    Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    // 예약등록
    @PostMapping("/reservation")
    public Response addReservation(@RequestBody HashMap<String, Object> map,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

//        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);
//
//        String jwt = jwtToken.getValue();
        String jwt = request.getHeader("Authorization");

        String email = jwtUtil.getUsername(jwt);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);
        map.put("customer_id", email);
        reservationService.insertReservation(map);
        response.setHeader("access_token", jwt);
        return new Response("200", "예약등록을 성공했습니다", null);
    }

    //예약취소
    @PutMapping("/reservation/{reservation_seq}")
    public Response delReservation(@RequestBody HashMap<String, Object> map,
                                   @PathVariable int reservation_seq,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        map.put("reservation_seq", reservation_seq);

//        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);

//        String jwt = jwtToken.getValue();
        String jwt = request.getHeader("Authorization");
        String email = jwtUtil.getUsername(jwt);
        map.put("customer_id", email);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);
        reservationService.deleteReservation(map);
        response.setHeader("access_token", jwt);
        return new Response("200", "예약취소를 성공했습니다", null);
    }

    //예약내역 조회
    @GetMapping("/reservation")
    public Response getReservation(HttpServletRequest request,
                                   HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<>();
//        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);
//
//        String jwt = jwtToken.getValue();
        String jwt = request.getHeader("Authorization");
        String email = jwtUtil.getUsername(jwt);
        map.put("customer_id", email);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);
        List<HashMap<String, Object>> result = reservationService.selectUserReservation(map);
        if(result.isEmpty()){
            result = null;
        }
        return new Response("200", "예약내역 조회를 성공했습니다.", result);
    }

    //예약 상세 조회
    @GetMapping("/reservation/{reservation_seq}")
    public Response getDetailReservation(@PathVariable int reservation_seq,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("reservation_seq", reservation_seq);
//        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);
//
//        String jwt = jwtToken.getValue();
        String jwt = request.getHeader("Authorization");
        String email = jwtUtil.getUsername(jwt);
        map.put("customer_id", email);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);
        HashMap<String, Object> result = reservationService.selectDetailUserReservation(map);
        return new Response("200", "예약내역 조회를 성공했습니다.", result);
    }

    //예약 취소 내역 조회 (API 수정)
    @GetMapping("/refund")
    public Response getRefund(HttpServletRequest request,
                              HttpServletResponse response) {
//        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);
        HashMap<String, Object> map = new HashMap<>();
//        String jwt = jwtToken.getValue();
        String jwt = request.getHeader("Authorization");
        String email = jwtUtil.getUsername(jwt);
        map.put("customer_id", email);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);
        List<HashMap<String, Object>> result = reservationService.selectRefund(map);
        if(result.isEmpty()){
            result = null;
        }
        return new Response("200", "예약내역 조회를 성공했습니다.", result);
    }

    //취소 내역 상세 조회
    @GetMapping("/refund/{refund_seq}")
    public Response getDetailRefund(@PathVariable int refund_seq,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("refund_seq", refund_seq);
//        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);

//        String jwt = jwtToken.getValue();
        String jwt = request.getHeader("Authorization");
        String email = jwtUtil.getUsername(jwt);
        map.put("customer_id", email);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);
        HashMap<String, Object> result = reservationService.selectDetailRefund(map);
        return new Response("200", "예약내역 조회를 성공했습니다.", result);
    }

    //관리자의 예약 내역 조회
    @GetMapping("/admin/reservation")
    public Response getAdminReservation(HttpServletRequest request,
                                        HttpServletResponse response) {
//        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);
        HashMap<String, Object> map = new HashMap<>();
//        String jwt = jwtToken.getValue();
        String jwt = request.getHeader("Authorization");
        String email = jwtUtil.getUsername(jwt);
        map.put("customer_id", email);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);
        List<HashMap<String, Object>> result = reservationService.selectAdminReservation();
        response.setHeader("access_token", jwt);
        if(result.isEmpty()){
            result = null;
        }
        for (HashMap<String, Object> i : result) {
            logger.info(i.toString());
        }
        logger.info("왜안더");
        return new Response("200", "예약내역 조회를 성공했습니다.", result);
    }

    //관리자의 상세 예약 내역 조회
    @GetMapping("/admin/reservation/{reservation_seq}")
    public Response getAdminDetailReservation(@PathVariable int reservation_seq,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {
//        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);
        HashMap<String, Object> map = new HashMap<>();
//        String jwt = jwtToken.getValue();
        String jwt = request.getHeader("Authorization");
        String email = jwtUtil.getUsername(jwt);
        map.put("customer_id", email);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);
        map.put("reservation_seq", reservation_seq);
        HashMap<String, Object> result = reservationService.selectDetailUserReservation(map);

        return new Response("200", "예약내역 조회를 성공했습니다.", result);
    }

    //회사의 예약 내역 조회
    @GetMapping("/company/{company_seq}/reservation")
    public Response getCompanyReservation(@PathVariable int company_seq,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("company_seq", company_seq);
//        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);

//        String jwt = jwtToken.getValue();
        String jwt = request.getHeader("Authorization");
        String email = jwtUtil.getUsername(jwt);
        map.put("customer_id", email);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);

        List<HashMap<String, Object>> result = reservationService.selectCompanyReservation(map);
        if(result.isEmpty()){
            result = null;
        }
        return new Response("200", "예약내역 조회를 성공했습니다.", result);
    }

    //회사의 상세 예약 내역 조회
    @GetMapping("/company/{company_seq}/reservation/{reservation_seq}")
    public Response getDetailCompanyReservation(@PathVariable int company_seq, @PathVariable int reservation_seq,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("reservation_seq", reservation_seq);
        map.put("company_seq", company_seq);
//        final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);

//        String jwt = jwtToken.getValue();
        String jwt = request.getHeader("Authorization");
        String email = jwtUtil.getUsername(jwt);
        map.put("customer_id", email);
        int customer_seq = userService.selectUserSeq(email);
        map.put("customer_seq", customer_seq);

        HashMap<String, Object> result = reservationService.selectDetailUserReservation(map);
        return new Response("200", "예약내역 조회를 성공했습니다.", result);
    }
    @GetMapping("/goods/{goods_detail_seq}/reservation")
    public Response goodsReservation(@PathVariable int goods_detail_seq){
        HashMap<String, Object> map = new HashMap<>();
        map.put("goods_detail_seq",goods_detail_seq);

        return new Response("200", "상품 조회를 성공했습니다.",reservationService.goodsReservation(map));
    }

}