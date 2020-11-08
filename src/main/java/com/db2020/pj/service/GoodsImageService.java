package com.db2020.pj.service;

import java.util.HashMap;
import java.util.List;

public interface GoodsImageService {
    public void insertGoodsImage(HashMap<String, Object> map);
    public List<HashMap<String, Object>> selectGoodsDetailImage(HashMap<String, Object> map);
    public List<HashMap<String, Object>> selectGoodsImage(HashMap<String, Object> map);
    public void updateGoodsImage(HashMap<String, Object> map);
    public void deleteGoodsImage(HashMap<String, Object> map);
}