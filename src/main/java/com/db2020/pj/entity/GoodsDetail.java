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
	int goods_detail_price;
	double goods_grade;
	int goods_detail_amount;
	String goods_detail_expression;
	String goods_image_path;
	int goods_post;
	String goods_address;
	String goods_detail_address;
	String goods_detail_tel;
	String goods_detail_view_yn;
	String goods_detail_createAt;
	String promotion_nm;
	int t_company_seq;
	int t_promotion_seq;
	int t_goods_type_seq;

	@Override
	public String toString() {
		return "GoodsDetail{" +
				"goods_detail_seq=" + goods_detail_seq +
				", goods_detail_nm='" + goods_detail_nm + '\'' +
				", goods_detail_price=" + goods_detail_price +
				", goods_grade=" + goods_grade +
				", goods_detail_amount=" + goods_detail_amount +
				", goods_detail_expression='" + goods_detail_expression + '\'' +
				", goods_image_path='" + goods_image_path + '\'' +
				", goods_post=" + goods_post +
				", goods_address='" + goods_address + '\'' +
				", goods_detail_address='" + goods_detail_address + '\'' +
				", goods_detail_tel='" + goods_detail_tel + '\'' +
				", goods_detail_view_yn='" + goods_detail_view_yn + '\'' +
				", goods_detail_createAt='" + goods_detail_createAt + '\'' +
				", promotion_nm='" + promotion_nm + '\'' +
				", t_company_seq=" + t_company_seq +
				", t_promotion_seq=" + t_promotion_seq +
				", t_goods_type_seq=" + t_goods_type_seq +
				'}';
	}
}
