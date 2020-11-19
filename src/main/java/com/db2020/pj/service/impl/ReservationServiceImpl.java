package com.db2020.pj.service.impl;

import com.db2020.pj.repository.ReservationRepository;
import com.db2020.pj.service.ReservationService;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    SqlSession sqlSession;
    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Override
    @Transactional
    public void insertReservation(HashMap<String, Object> map) {

        ReservationRepository reservationRepository = new ReservationRepository(sqlSession);

        logger.info(map.get("reservation_total_price").toString());
        logger.info(map.get("approval_mileage").toString());
        int reservation_total_price = Integer.parseInt(map.get("reservation_total_price").toString());
        logger.info("111");
        String discountType = map.get("promotion_discountType").toString();
        logger.info("222");
        int discount = Integer.parseInt(map.get("promotion_discount").toString());
        logger.info("333");
        int mileage = Integer.parseInt(map.get("approval_mileage").toString());
        logger.info("444");


        reservationRepository.insertReservation(map);
        logger.info("1번째 쿼리 성공");
        String seq = reservationRepository.selectReservationSeq(map);
        logger.info("2번째 쿼리 성공");
        map.put("reservation_seq",seq);
        map.put("price",reservation_total_price);

        int sales_price;
        if(discountType.equals("price")){
            sales_price = reservation_total_price-discount;
        }
        else{
            sales_price = reservation_total_price-(reservation_total_price/discount);
        }
        map.put("sales_price",sales_price);
        map.put("total_price",sales_price-mileage);
        logger.info("3번째 쿼리 시도중");
        reservationRepository.insertApproval(map);
        logger.info("3번째 쿼리 성공");
    }

    @Override
    @Transactional
    public void deleteReservation(HashMap<String, Object> map) {
        ReservationRepository reservationRepository = new ReservationRepository(sqlSession);

        reservationRepository.updateReservation(map);
        reservationRepository.updateApproval(map);
        reservationRepository.insertRefund(map);
    }

    @Override
    public List<HashMap<String, Object>> selectUserReservation(HashMap<String, Object> map) {
        ReservationRepository reservationRepository = new ReservationRepository(sqlSession);
        return reservationRepository.selectUserReservation(map);
    }

    @Override
    public HashMap<String, Object> selectDetailUserReservation(HashMap<String, Object> map) {
        ReservationRepository reservationRepository = new ReservationRepository(sqlSession);
        return reservationRepository.selectDetailUserReservation(map);
    }

    @Override
    public List<HashMap<String, Object>> selectRefund(HashMap<String, Object> map) {
        ReservationRepository reservationRepository = new ReservationRepository(sqlSession);
        return reservationRepository.selectRefund(map);
    }

    @Override
    public List<HashMap<String, Object>> selectAdminReservation() {
        ReservationRepository reservationRepository = new ReservationRepository(sqlSession);
        return reservationRepository.selectAdminReservation();
    }

    @Override
    public List<HashMap<String, Object>> selectCompanyReservation(HashMap<String, Object> map) {
        ReservationRepository reservationRepository = new ReservationRepository(sqlSession);

        List<HashMap<String, Object>> request = reservationRepository.selectCompanyReservation(map);

        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyyMMdd");
        Date today = new Date(System.currentTimeMillis());
        int day = Integer.parseInt(dateFormate.format(today));
        int startDay;
        int endDay ;

        int scheduled=0;
        int complete=0;
        int count=0;
        int money=0;
        for(HashMap<String, Object> data : request){
            startDay = Integer.parseInt(data.get("reservation_st").toString().replace("-", ""));
            endDay = Integer.parseInt(data.get("reservation_end").toString().replace("-",""));

            logger.info(day+ " |||||||" + startDay + "||||||||||||||||" + endDay);
            if(day<startDay){
                logger.info(day+ " |||||||" + scheduled);
                scheduled++;
            }
            if(day>endDay){
                logger.info(day+ " |||||||" + complete);
                complete++;
            }
            count++;
            money = money + Integer.parseInt(data.get("reservation_total_price").toString());
        }
        request.get(0).put("scheduled",scheduled);
        request.get(0).put("complete", complete);
        request.get(0).put("count", complete);
        request.get(0).put("AllMoney", money);

        return request;
    }

    @Override
    public HashMap<String, Object> selectDetailRefund(HashMap<String, Object> map) {
        ReservationRepository reservationRepository = new ReservationRepository(sqlSession);
        return reservationRepository.selectDetailRefund(map);
    }

    @Override
    public List<HashMap<String, Object>> goodsReservation(HashMap<String, Object> map){
        ReservationRepository reservationRepository = new ReservationRepository(sqlSession);
        return reservationRepository.goodsReservation(map);
    }
}