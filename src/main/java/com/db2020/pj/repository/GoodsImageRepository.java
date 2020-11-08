package com.db2020.pj.repository;

import com.db2020.pj.mapper.GoodsImageMapper;
import com.db2020.pj.mapper.ReservationMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

public class GoodsImageRepository {
    GoodsImageMapper mapper;
    SqlSession sqlSession = null;

    public GoodsImageRepository(SqlSession sqlSession){
        this.sqlSession = sqlSession;
        mapper = sqlSession.getMapper(GoodsImageMapper.class);
    }
    public void insertGoodsImage(HashMap<String, Object> map){
        mapper.insertGoodsImage(map);
    }

    public List<HashMap<String, Object>> selectGoodsDetailImage(HashMap<String, Object> map){
        return mapper.selectGoodsDetailImage(map);
    }
    public List<HashMap<String, Object>> selectGoodsImage(HashMap<String, Object> map){
        return mapper.selectGoodsImage(map);
    }
    public void updateGoodsImage(HashMap<String, Object> map){
        mapper.updateGoodsImage(map);
    }
    public void deleteGoodsImage(HashMap<String, Object> map){
        mapper.deleteGoodsImage(map);
    }
}
