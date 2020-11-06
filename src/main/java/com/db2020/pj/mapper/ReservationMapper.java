package com.db2020.pj.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReservationMapper {
    public void insertReservation(HashMap<String, Object> map);
    public String selectReservationSeq(HashMap<String, Object> map);
    public void insertApproval(HashMap<String, Object> map);
    public void updateReservation(HashMap<String, Object> map);
    public void updateApproval(HashMap<String, Object> map);
    public void insertRefund(HashMap<String, Object> map);
    public List<HashMap<String, Object>> selectUserReservation(HashMap<String, Object> map);
    public HashMap<String, Object> selectDetailUserReservation(HashMap<String, Object> map);
    public HashMap<String, Object> selectDetailRefund(HashMap<String ,Object> map);
    public List<HashMap<String, Object>> selectRefund(HashMap<String, Object> map);
    public List<HashMap<String, Object>> selectAdminReservation();
    public List<HashMap<String, Object>> selectCompanyReservation(HashMap<String, Object> map);
    public List<HashMap<String, Object>> reserve_date(Map<String, Object> param);

}
