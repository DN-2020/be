package com.db2020.pj.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db2020.pj.entity.Goods;
import com.db2020.pj.entity.GoodsDetail;

public interface GoodsRepository {
	
//	public void register(Map<String, String> goods);
	
	public void detail_register(GoodsDetail goodsDetail);
	
//	public Goods selectOne(int goods_seq);
	
	public GoodsDetail selectOne(HashMap<String, Object> query);
	
//	public Map<String, Object> reserve_date(Map<String, Object> reserve_date);
//
	public List<GoodsDetail> selectList(Map<String, Object> parameter);
	
	public List<GoodsDetail> selectAdminList(Map<String, Object> parameter);
	
	public List<GoodsDetail> selectDetailList(int goods_seq);
	
	public void goodsIsView(Map<String, Object> parameter);
	
//	public void goodsDetailIsView(Map<String, Object> parameter, String goods_detail_view_yn);
	
	public void update(Map<String, Object> goods);
	
//	public void detail_update(Map<String, Object> goods);
	
	public void delete(String goods_seq);
	
	public void detail_delete(Map<String, String> parameter);

	public List<GoodsDetail> selectSearch(HashMap<String, Object> map);

	public List<GoodsDetail> selectCategorySearch(HashMap<String, Object> map);

	public List<HashMap<String, Object>> reserve_date(Map<String, Object> param);

}

