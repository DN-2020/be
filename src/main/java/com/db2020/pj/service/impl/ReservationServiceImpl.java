package com.db2020.pj.service.impl;

import com.db2020.pj.repository.ReservationRepository;
import com.db2020.pj.service.ReservationService;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    SqlSession sqlSession;
    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Override
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
        return reservationRepository.selectCompanyReservation(map);
    }

    @Override
    public HashMap<String, Object> selectDetailRefund(HashMap<String, Object> map) {
        ReservationRepository reservationRepository = new ReservationRepository(sqlSession);
        return reservationRepository.selectDetailRefund(map);
    }
}
