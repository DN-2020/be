package com.db2020.pj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PromotionGoodsDTO {
	private int promotion_seq;
	private String promotion_nm;
	private String promotion_discount;
	private String promotion_discountType;
	private String promotion_st;
	private String promotion_end;
	
	Object goods_detail;
}
