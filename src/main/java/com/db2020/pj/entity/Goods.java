package com.db2020.pj.entity;

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
public class Goods {

	private int goods_seq;
	private String type_nm;
	private String goods_nm;
	private int goods_post;
	private String goods_address;
	private String goods_detail_address;
	private String goods_tel;
	private String view_yn;
	private int t_goods_type_seq;
	private int t_company_seq;
	
	@Override
	public String toString() {
		return "Goods [goods_seq=" + goods_seq + ", type_nm=" + type_nm + ", goods_nm=" + goods_nm + ", goods_post="
				+ goods_post + ", goods_address=" + goods_address + ", goods_detail_address=" + goods_detail_address
				+ ", goods_tel=" + goods_tel + ", view_yn=" + view_yn + ", t_goods_type_seq=" + t_goods_type_seq
				+ ", t_company_seq=" + t_company_seq + "]";
	}
	
}
