package com.db2020.pj.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db2020.pj.controller.GoodsController;
import org.apache.ibatis.session.SqlSession;
import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db2020.pj.entity.Goods;
import com.db2020.pj.entity.GoodsDetail;
import com.db2020.pj.mapper.ReservationMapper;

@Repository
public class GoodsRepositoryImpl implements GoodsRepository {

	private Logger logger = LoggerFactory.getLogger(GoodsRepository.class);

	@Autowired
	SqlSession sqlSession;
	
	ReservationMapper reservationMapper;

//	@Override
//	public void register(Map<String, String> goods) {
//
//		sqlSession.insert("goods.register", goods);
//
//	}

	@Override
	public void detail_register(GoodsDetail detail_goods) {
		sqlSession.insert("goods.detail_register", detail_goods);

		int goods_detail_seq = sqlSession.selectOne("goods.select_seq");

		for(HashMap<String, Object> param : detail_goods.getGoods_detail_image()) {
			param.put("goods_detail_seq", goods_detail_seq);

			sqlSession.insert("goods.image_register", param);
		}
	}

//	@Override
//	public Goods selectOne(int goods_seq) {
//
//		Goods goods = sqlSession.selectOne("goods.select_goods", goods_seq);
//
//		if(goods == null) {
////			throw new CGoodsNotException();
//		}
//		return goods;
//	}

	@Override
	public GoodsDetail selectOne(HashMap<String, Object> goods_detail) {
//		GoodsDetail goodsDetail = sqlSession.selectOne("goods.detail_select", goods_detail);

		GoodsDetail goodsDetail = sqlSession.selectOne("goods.detail_goods", goods_detail);

		System.out.println(goodsDetail.toString());
		return goodsDetail;
	}

//	@Override
//	public Map<String, Object> reserve_date(Map<String, Object> reserve_date) {
//		Map<String, Object> list = sqlSession.selectMap("goods.mapList", reserve_date, "reserve_date");
//
//		return list;
//	}

	@Override
	public List<GoodsDetail> selectList(Map<String, Object> parameter) {

		List<GoodsDetail> goodsList = null;
		
		if(parameter.get("goods_detail_nm") != null) {
			goodsList = sqlSession.selectList("goods.select_sgoodsList", parameter);
		}
		else if(parameter.get("t_company_seq") != null) {
			goodsList = sqlSession.selectList("goods.select_cgoodsList", parameter);
			System.out.println(goodsList.toString());
		}
		return goodsList;
	}

	
	@Override
	public List<GoodsDetail> selectAdminList(Map<String, Object> parameter) {
		
		List<GoodsDetail> goodsList;
		
		if(parameter.get("goods_detail_nm") != null && parameter.get("company_nm") != null) {
			goodsList = sqlSession.selectList("goods.two_goodsList", parameter);
			logger.info("상품 이름 & 회사 이름 검색: " + goodsList.toString());
		} else if(parameter.get("goods_detail_nm") != null) {
			goodsList = sqlSession.selectList("goods.gn_goodsList", parameter);
			logger.info("상품 이름 검색: " + goodsList.toString());
		} else if(parameter.get("company_nm") != null) {
			goodsList = sqlSession.selectList("goods.cn_goodsList", parameter);
			logger.info("회사 이름 검색: " + goodsList.toString());
		} else {
			goodsList = sqlSession.selectList("goods.select_goodsList", parameter);
			logger.info("상품 전체 검색: " + goodsList.toString());
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
		if(parameter.get("goods_detail_view_yn").equals("Y")) {
			sqlSession.update("goods.isViewY", parameter);
		}
		else {
			sqlSession.update("goods.isViewN", parameter);
		}
		
	}

//	@Override
//	public void goodsDetailIsView(Map<String, Object> parameter, String goods_detail_view_yn) {
//
//		System.out.println(goods_detail_view_yn);
//		if(goods_detail_view_yn.equals("Y")) {
//			sqlSession.update("goods.detail_isViewY", parameter);
//		}
//		else {
//			System.out.println("N");
//			sqlSession.update("goods.detail_isViewN", parameter);
//		}
//	}

	@Override
	public void update(Map<String, Object> goods) {
		sqlSession.update("goods.update", goods);
	}

//	@Override
//	public void detail_update(Map<String, Object> goods) {
//		sqlSession.update("goods.detail_update", goods);
//	}

	@Override
	public void delete(String goods_seq) {
		sqlSession.delete("goods.delete_first", goods_seq);
//		sqlSession.delete("goods.delete_second", goods_seq);
	}

	@Override
	public void detail_delete(Map<String, String> parameter) {
		sqlSession.delete("goods.detail_delete", parameter);
	}

	@Override
	public List<GoodsDetail> selectSearch(HashMap<String, Object> map) {
		return sqlSession.selectList("goods.selectSearch", map);
	}

	@Override
	public List<GoodsDetail> selectCategorySearch(HashMap<String, Object> map) {
		return sqlSession.selectList("goods.selectCategorySearch", map);
	}

	@Override
	public List<HashMap<String, Object>> reserve_date(Map<String, Object> param) {

		reservationMapper = sqlSession.getMapper(ReservationMapper.class);
		
		return reservationMapper.reserve_date(param);
	}
	
	
}
