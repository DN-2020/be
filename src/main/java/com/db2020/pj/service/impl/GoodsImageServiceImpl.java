package com.db2020.pj.service.impl;

import com.db2020.pj.entity.Goods;
import com.db2020.pj.repository.GoodsImageRepository;
import com.db2020.pj.service.GoodsImageService;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GoodsImageServiceImpl implements GoodsImageService {
    @Autowired
    SqlSession sqlSession;
    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);


    public void insertGoodsImage(HashMap<String, Object> map) {
        GoodsImageRepository repository = new GoodsImageRepository(sqlSession);
        repository.insertGoodsImage(map);
    }
    public List<HashMap<String, Object>> selectGoodsDetailImage(HashMap<String, Object> map){
        GoodsImageRepository repository = new GoodsImageRepository(sqlSession);
        return repository.selectGoodsDetailImage(map);

    }
    public List<HashMap<String, Object>> selectGoodsImage(HashMap<String, Object> map){
        GoodsImageRepository repository = new GoodsImageRepository(sqlSession);
        return repository.selectGoodsImage(map);
    }
    public void updateGoodsImage(HashMap<String, Object> map){
        GoodsImageRepository repository = new GoodsImageRepository(sqlSession);
        repository.updateGoodsImage(map);
    }
    public void deleteGoodsImage(HashMap<String, Object> map){
        GoodsImageRepository repository = new GoodsImageRepository(sqlSession);
        repository.deleteGoodsImage(map);
    }
    public HashMap<String, Object> selectImage(HashMap<String, Object> map){
        GoodsImageRepository repository = new GoodsImageRepository(sqlSession);
        return repository.selectImage(map);
    }
}
