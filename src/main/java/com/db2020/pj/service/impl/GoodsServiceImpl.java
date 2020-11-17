package com.db2020.pj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db2020.pj.repository.GoodsImageRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db2020.pj.entity.Goods;
import com.db2020.pj.entity.GoodsDetail;
import com.db2020.pj.repository.GoodsRepository;
import com.db2020.pj.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService{

	@Autowired
	GoodsRepository goodsRepository;
	@Autowired
	SqlSession sqlSession;

	
//	@Override
//	public void register(Map<String, String> goods) {
//		goodsRepository.register(goods);
//	}

	@Override
	public void detail_register(GoodsDetail goodsDetail) {

		goodsRepository.detail_register(goodsDetail);
	}

//	@Override
//	public Goods selectOne(int goods_seq) {
//		Goods goods = goodsRepository.selectOne(goods_seq);
//
//		return goods;
//	}
	
	@Override
	public GoodsDetail selectOne(HashMap<String, Object> goods_detail) {
		GoodsDetail goodsDetail = goodsRepository.selectOne(goods_detail);
		System.out.println(goodsDetail);
		GoodsImageRepository repository = new GoodsImageRepository(sqlSession);

		goodsDetail.setGoods_detail_image(repository.selectGoodsDetailImage(goods_detail));
		return goodsDetail;
	}

//	@Override
//	public Map<String, Object> reserve_date(Map<String, Object> reserve_date) {
//		Map<String, Object> list = goodsRepository.reserve_date(reserve_date);
//		return list;
//	}

	@Override
	public List<GoodsDetail> selectList(Map<String, Object> parameter) {
		List<GoodsDetail> goodsList = goodsRepository.selectList(parameter);
		
		return goodsList;
	}

	@Override
	public List<GoodsDetail> selectAdminList(Map<String, Object> parameter) {
		List<GoodsDetail> goodsList = goodsRepository.selectAdminList(parameter);
		return goodsList;
	}

	@Override
	public List<GoodsDetail> selectDetailList(int goods_seq) {
		List<GoodsDetail> goodsDetailList = goodsRepository.selectDetailList(goods_seq);
		return goodsDetailList;
	}
	
	@Override
	public void goodsIsView(Map<String, Object> parameter) {
		goodsRepository.goodsIsView(parameter);
		
	}

//	@Override
//	public void goodsDetailIsView(Map<String, Object> parameter, String goods_detail_view_yn) {
//		goodsRepository.goodsDetailIsView(parameter, goods_detail_view_yn);
//	}

	@Override
	public void update(Map<String, Object> goods) {
		goodsRepository.update(goods);
		
	}
//	@Override
//	public void detail_update(Map<String, Object> goods) {
//		goodsRepository.detail_update(goods);
//	}

	@Override
	public void delete(String goods_seq) {
		goodsRepository.delete(goods_seq);		
	}

//	@Override
//	public void detail_delete(Map<String, String> parameter) {
//		goodsRepository.detail_delete(parameter);
//	}

	@Override
	public List<Goods> selectSearch(HashMap<String, Object> map) {
		int limit = Integer.parseInt(map.get("limit").toString());
		map.put("limit", limit);
		return goodsRepository.selectSearch(map);
	}

	@Override
	public List<Goods> selectCategorySearch(HashMap<String, Object> map) {
		int limit = Integer.parseInt(map.get("limit").toString());;
		map.put("limit", limit);
		return goodsRepository.selectCategorySearch(map);
	}

	@Override
	public List<HashMap<String, Object>> reserve_date(Map<String, Object> param) {

		return goodsRepository.reserve_date(param);
	}
	
	
}
