package com.db2020.pj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class GoodsDetail {
	private int goods_detail_seq;
	private String goods_detail_nm;
	private int goods_detail_price;
	private String goods_detail_expression;
}
