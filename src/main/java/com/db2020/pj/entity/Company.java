package com.db2020.pj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Company {

	private int company_seq;
	private String company_nm;
	private String company_tel;
	private String company_post;
	private String company_address;
	private String company_detail_address;
	private String company_email;
	private String company_num;
	private String company_regist_num;
	private String company_createAt;
	private String company_updateAt;
	private String company_role;
}
