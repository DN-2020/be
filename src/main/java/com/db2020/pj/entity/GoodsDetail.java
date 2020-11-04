package com.db2020.pj.entity;

import com.db2020.pj.entity.Goods.GoodsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 

public class GoodsDetail {

	int goods_detail_seq;
	String goods_detail_nm;
	String promotion_nm;
	int goods_detail_price;
	int goods_detail_amount;
	String goods_detail_view_yn;
	String goods_detail_expression;
	int t_goods_seq;
	int t_promotion_seq;
	
	
	
	
	
}
