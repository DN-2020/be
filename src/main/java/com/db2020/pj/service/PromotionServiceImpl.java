package com.db2020.pj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db2020.pj.entity.GoodsDetailDTO;
import com.db2020.pj.entity.Promotion;
import com.db2020.pj.entity.PromotionGoodsDTO;
import com.db2020.pj.repository.PromotionRepository;

@Service
public class PromotionServiceImpl implements PromotionService{

	@Autowired
	PromotionRepository promotionRepository;
	
	@Override
	public List<Promotion> promotionList() {
		// TODO Auto-generated method stub
		return promotionRepository.promotionList();
	}

	@Override
	public int promotionInsert(Promotion promotion) {
		// TODO Auto-generated method stub
		return promotionRepository.promotionInsert(promotion);
	}

	@Override
	public int promotionUpdate(Promotion promotion) {
		// TODO Auto-generated method stub
		return promotionRepository.promotionUpdate(promotion);
	}

	@Override
	public int promotionDelete(int promotion_seq) {
		// TODO Auto-generated method stub
		return promotionRepository.promotionDelete(promotion_seq);
	}

	@Override
	public PromotionGoodsDTO promotionDetail(int promotion_seq) {
		// TODO Auto-generated method stub
		return promotionRepository.promotionDetail(promotion_seq);
	}
	
	@Override
	public List<GoodsDetailDTO> promotionGoodsList(int promotion_seq) {
		// TODO Auto-generated method stub
		return promotionRepository.promotionGoodsList(promotion_seq);
	}

	

}
