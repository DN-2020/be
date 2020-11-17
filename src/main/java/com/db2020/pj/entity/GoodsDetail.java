package com.db2020.pj.entity;

import com.db2020.pj.entity.Goods.GoodsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

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
	List<HashMap<String, Object>> goods_detail_image;

	@Override
	public String toString() {
		return "GoodsDetail{" +
				"goods_detail_seq=" + goods_detail_seq +
				", goods_detail_nm='" + goods_detail_nm + '\'' +
				", promotion_nm='" + promotion_nm + '\'' +
				", goods_detail_price=" + goods_detail_price +
				", goods_detail_amount=" + goods_detail_amount +
				", goods_detail_view_yn='" + goods_detail_view_yn + '\'' +
				", goods_detail_expression='" + goods_detail_expression + '\'' +
				", t_goods_seq=" + t_goods_seq +
				", t_promotion_seq=" + t_promotion_seq +
				", goods_detail_image=" + goods_detail_image +
				'}';
	}
}
