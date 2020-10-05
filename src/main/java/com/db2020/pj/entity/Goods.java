package com.db2020.pj.entity;

import com.db2020.pj.entity.Customer.CustomerBuilder;

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
	private String goods_address_detail;
	private String goods_tel;
	private String view_yn;
	private int t_goods_type_seq;
	private int t_company_seq;
	
}
