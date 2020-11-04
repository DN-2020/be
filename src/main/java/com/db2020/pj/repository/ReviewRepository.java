package com.db2020.pj.repository;

import com.db2020.pj.mapper.ReviewMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

public class ReviewRepository {
	ReviewMapper mapper ;
    SqlSession sqlSession;

    public ReviewRepository(SqlSession sqlSession){
        this.sqlSession = sqlSession;
        mapper = sqlSession.getMapper(ReviewMapper.class);
    }

    public void insertReview(HashMap<String, Object> map){
        mapper.insertReview(map);
    }
    public List<HashMap<String, Object>> selectListReview(HashMap<String, Object> map ){
        return mapper.selectListReview(map);
    }
    public List<HashMap<String, Object>> selectUserReview(HashMap<String, Object> map){
        return mapper.selectUserReview(map);
    }
    public HashMap<String, Object> selectDetailReview(HashMap<String, Object> map){
        return mapper.selectDetailReview(map);
    }
    public void updateReview(HashMap<String, Object> map){
        mapper.updateReview(map);
    }
}
