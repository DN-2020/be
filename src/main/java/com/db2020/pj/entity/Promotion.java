package com.db2020.pj.entity;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Promotion {
	private int promotion_seq;
	private String promotion_nm;
	private String promotion_discount;
	private String promotion_discountType;
	private String promotion_st;
	private String promotion_end;
}
