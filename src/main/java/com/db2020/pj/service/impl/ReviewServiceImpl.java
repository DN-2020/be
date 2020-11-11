package com.db2020.pj.service.impl;

import com.db2020.pj.repository.ReviewRepository;
import com.db2020.pj.service.ReviewService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    SqlSession sqlSession;
    @Override
    public void insertReview(HashMap<String, Object> map) {
        ReviewRepository reviewRepository = new ReviewRepository(sqlSession);
        reviewRepository.insertReview(map);

    }

    @Override
    public List<HashMap<String, Object>> selectListReview(HashMap<String, Object> map) {
        ReviewRepository reviewRepository = new ReviewRepository(sqlSession);
        return reviewRepository.selectListReview(map);
    }

    @Override
    public List<HashMap<String, Object>> selectUserReview(HashMap<String, Object> map) {
        ReviewRepository reviewRepository = new ReviewRepository(sqlSession);
        return reviewRepository.selectUserReview(map);
    }

    @Override
    public HashMap<String, Object> selectDetailReview(HashMap<String, Object> map) {
        ReviewRepository reviewRepository = new ReviewRepository(sqlSession);
        return reviewRepository.selectDetailReview(map);
    }

    @Override
    public void updateReview(HashMap<String, Object> map) {
        ReviewRepository reviewRepository = new ReviewRepository(sqlSession);
        reviewRepository.updateReview(map);
    }

    @Override
    public List<HashMap<String, Object>> selectCompanyReviewList(HashMap<String, Object> map) {
        ReviewRepository reviewRepository = new ReviewRepository(sqlSession);
        return reviewRepository.selectCompanyReviewList(map);
    }

    @Override
    public List<HashMap<String, Object>> selectGoodsReviewList(HashMap<String, Object> map) {
        ReviewRepository reviewRepository = new ReviewRepository(sqlSession);
        return reviewRepository.selectGoodsReviewList(map);
    }

    @Override
    public List<HashMap<String, Object>> selectGoodsDetailReviewList(HashMap<String, Object> map) {
        ReviewRepository reviewRepository = new ReviewRepository(sqlSession);
        return reviewRepository.selectGoodsDetailReviewList(map);
    }
}
