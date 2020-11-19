package com.db2020.pj.service;

import java.util.HashMap;
import java.util.List;

public interface ReservationService {
    public void insertReservation(HashMap<String, Object> map);
    public void deleteReservation(HashMap<String, Object> map);
    public List<HashMap<String, Object>> selectUserReservation(HashMap<String, Object> map);
    public HashMap<String, Object> selectDetailUserReservation(HashMap<String, Object> map);
    public List<HashMap<String, Object>> selectRefund(HashMap<String, Object> map);
    public HashMap<String, Object> selectDetailRefund(HashMap<String, Object> map);
    public List<HashMap<String, Object>> selectAdminReservation();
    public List<HashMap<String, Object>> selectCompanyReservation(HashMap<String, Object> map);

    public List<HashMap<String, Object>> goodsReservation(HashMap<String, Object> map);
}
