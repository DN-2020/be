package com.db2020.pj.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db2020.pj.entity.Goods;
import com.db2020.pj.entity.GoodsDetail;

@Repository
public class GoodsRepositoryImpl implements GoodsRepository {

	@Autowired
	SqlSession sqlSession;

	@Override
	public void register(Map<String, String> goods) {
		sqlSession.insert("goods.register", goods);
	}

	@Override
	public void detail_register(Map<String, String> detail_goods) {
		sqlSession.insert("goods.detail_register", detail_goods);
	}

	@Override
	public Goods selectOne(int goods_seq) {
		Goods goods = sqlSession.selectOne("goods.select_goods", goods_seq);

		return goods;
	}

	@Override
	public GoodsDetail selectOne(Map<String, Integer> goods_detail) {
//		GoodsDetail goodsDetail = sqlSession.selectOne("goods.detail_select", goods_detail);

		GoodsDetail goodsDetail = sqlSession.selectOne("goods.detail_goods", goods_detail);

		return goodsDetail;
	}

	@Override
	public List<Goods> selectList(Map<String, Object> parameter) {

		List<Goods> goodsList;
		
		if(parameter.get("t_company_seq") != null) {
			goodsList = sqlSession.selectList("goods.select_cgoodsList", parameter);
		} 
		else {
			goodsList = sqlSession.selectList("goods.select_goodsList");
		}
		
		return goodsList;
	}

	@Override
	public List<GoodsDetail> selectDetailList(int goods_seq) {
		List<GoodsDetail> goodsList = sqlSession.selectList("goods.select_detail_goodsList", goods_seq);

		System.out.println(goodsList.toString());
		return goodsList;
	}

	@Override
	public void goodsIsView(Map<String, Object> parameter) {

		if(parameter.get("goods_view_yn").equals("Y")) {
			System.out.println("Rr");
			sqlSession.update("goods.isViewY", parameter);
		}
		else {
			sqlSession.update("goods.isViewN", parameter);
		}
		
	}

	@Override
	public void goodsDetailIsView(Map<String, Object> parameter) {
		
		if(parameter.get("goods_detail_view_yn").equals("Y")) {
			sqlSession.update("goods.detail_isViewY", parameter);
		}
		else {
			sqlSession.update("goods.detail_isViewN", parameter);
		}
	}

	@Override
	public void update(Map<String, Object> goods) {
		sqlSession.update("goods.update", goods);
	}

	@Override
	public void detail_update(Map<String, Object> goods) {
		
		System.out.println(goods.get("goods_detail_nm"));
		sqlSession.update("goods.detail_update", goods);
	}

	@Override
	public void delete(String goods_seq) {
		sqlSession.delete("goods.delete_first", goods_seq);
		sqlSession.delete("goods.delete_second", goods_seq);
	}

	@Override
	public void detail_delete(Map<String, String> parameter) {
		sqlSession.delete("goods.detail_delete", parameter);
	}

	@Override
	public List<Goods> selectSearch(HashMap<String, Object> map) {
		return sqlSession.selectList("goods.selectSearch", map);
	}

	@Override
	public List<Goods> selectCategorySearch(HashMap<String, Object> map) {
		return sqlSession.selectList("goods.selectCategorySearch", map);
	}
}
