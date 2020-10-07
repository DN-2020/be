package com.db2020.pj.service.impl;

import java.util.List;
import java.util.Map;

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
	
	@Override
	public void register(Map<String, String> goods) {
		goodsRepository.register(goods);
	}

	@Override
	public void detail_register(Map<String, String> detail_goods) {
		goodsRepository.detail_register(detail_goods);
	}

	@Override
	public Goods selectOne(int goods_seq) {
		Goods goods = goodsRepository.selectOne(goods_seq);
		
		return goods;
	}
	
	@Override
	public GoodsDetail selectOne(Map<String, Integer> goods_detail) {
		GoodsDetail goodsDetail = goodsRepository.selectOne(goods_detail);
		
		return goodsDetail;
	}

//	@Override
//	public Map<String, Object> reserve_date(Map<String, Object> reserve_date) {
//		Map<String, Object> list = goodsRepository.reserve_date(reserve_date);
//		return list;
//	}

	@Override
	public List<Goods> selectList(Map<String, Object> parameter) {
		List<Goods> goodsList = goodsRepository.selectList(parameter);
		
		return goodsList;
	}

	@Override
	public List<Goods> selectAdminList(Map<String, Object> parameter) {
		List<Goods> goodsList = goodsRepository.selectAdminList(parameter);
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

	@Override
	public void goodsDetailIsView(Map<String, Object> parameter) {
		goodsRepository.goodsDetailIsView(parameter);
	}

	@Override
	public void update(Map<String, Object> goods) {
		goodsRepository.update(goods);
		
	}
	
	@Override
	public void detail_update(Map<String, Object> goods) {
		goodsRepository.detail_update(goods);
	}

	@Override
	public void delete(String goods_seq) {
		goodsRepository.delete(goods_seq);		
	}

	@Override
	public void detail_delete(Map<String, String> parameter) {
		goodsRepository.detail_delete(parameter);
	}
	
}
