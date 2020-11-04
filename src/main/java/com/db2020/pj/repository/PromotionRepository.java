package com.db2020.pj.repository;

import java.util.List;

import com.db2020.pj.entity.GoodsDetailDTO;
import com.db2020.pj.entity.Promotion;
import com.db2020.pj.entity.PromotionGoodsDTO;

public interface PromotionRepository {

	List<Promotion> promotionList();
	
	int promotionInsert(Promotion promotion);
	
	int promotionUpdate(Promotion promotion);
	
	int promotionDelete(int promotion_seq);
	
	PromotionGoodsDTO promotionDetail(int promotion_seq);
	
	List<GoodsDetailDTO> promotionGoodsList(int promotion_seq);
}
