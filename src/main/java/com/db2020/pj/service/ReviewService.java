package com.db2020.pj.service;

import java.util.HashMap;
import java.util.List;

public interface ReviewService {
    public void insertReview(HashMap<String, Object> map);
    public List<HashMap<String, Object>> selectListReview(HashMap<String, Object> map );
    public List<HashMap<String, Object>> selectUserReview(HashMap<String, Object> map);
    public HashMap<String, Object> selectDetailReview(HashMap<String, Object> map);
    public void updateReview(HashMap<String,Object> map);
    public List<HashMap<String, Object>> selectCompanyReviewList(HashMap<String, Object> map);
    public List<HashMap<String, Object>> selectGoodsReviewList(HashMap<String, Object> map);
}