package com.db2020.pj.repository;

import com.db2020.pj.mapper.ReservationMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

public class ReservationRepository {

    ReservationMapper mapper;
    SqlSession sqlSession = null;

    public ReservationRepository(SqlSession sqlSession){
        this.sqlSession = sqlSession;
        mapper = sqlSession.getMapper(ReservationMapper.class);
    }
    public void insertReservation(HashMap<String, Object> map){
        mapper.insertReservation(map);
    }
    public String selectReservationSeq(HashMap<String, Object> map){
        return mapper.selectReservationSeq(map);
    }
    public void insertApproval(HashMap<String, Object> map){
        mapper.insertApproval(map);
    }
    public void updateReservation(HashMap<String, Object> map){
        mapper.updateReservation(map);
    }
    public void updateApproval(HashMap<String, Object> map){
        mapper.updateApproval(map);
    }
    public void insertRefund(HashMap<String, Object> map){
        mapper.insertRefund(map);
    }
    public List<HashMap<String, Object>> selectUserReservation(HashMap<String, Object> map){
        return mapper.selectUserReservation(map);
    }
    public HashMap<String, Object> selectDetailUserReservation(HashMap<String, Object> map){
        return mapper.selectDetailUserReservation(map);
    }
    public List<HashMap<String, Object>> selectRefund(HashMap<String, Object> map){
        return mapper.selectRefund(map);
    }
    public HashMap<String, Object> selectDetailRefund(HashMap<String, Object> map) { return mapper.selectDetailRefund(map);}
    public List<HashMap<String, Object>> selectAdminReservation(){
        return mapper.selectAdminReservation();
    }
    public List<HashMap<String, Object>> selectCompanyReservation(HashMap<String, Object> map){
        return mapper.selectCompanyReservation(map);
    }

}
