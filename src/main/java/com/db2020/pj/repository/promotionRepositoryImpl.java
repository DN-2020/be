package com.db2020.pj.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db2020.pj.entity.GoodsDetailDTO;
import com.db2020.pj.entity.Promotion;
import com.db2020.pj.entity.PromotionGoodsDTO;

@Repository
public class promotionRepositoryImpl implements PromotionRepository{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Promotion> promotionList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("promotionDAO.promotionList");
	}

	@Override
	public int promotionInsert(Promotion promotion) {
		// TODO Auto-generated method stub
		return sqlSession.insert("promotionDAO.promotionInsert",promotion);
	}

	@Override
	public int promotionUpdate(Promotion promotion) {
		// TODO Auto-generated method stub
		return sqlSession.update("promotionDAO.promotionUpdate",promotion);
	}

	@Override
	public int promotionDelete(int promotion_seq) {
		// TODO Auto-generated method stub
		return sqlSession.delete("promotionDAO.promotionDelete",promotion_seq);
	}
	
	@Override
	public PromotionGoodsDTO promotionDetail(int promotion_seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("promotionDAO.promotionDetail",promotion_seq);
	}

	@Override
	public List<GoodsDetailDTO> promotionGoodsList(int promotion_seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("promotionDAO.promotionGoodsList",promotion_seq);
		
	}

	

}
