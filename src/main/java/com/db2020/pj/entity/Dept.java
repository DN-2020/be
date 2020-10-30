package com.db2020.pj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Dept {
	
	private int dept_seq;
	private String dept_nm;
	private String dept_path;
	private String dept_manager;
	private String dept_createAt;
	private String dept_updateAt;
	private int company_seq;
	private int t_dept_seq;
	private int t_company_seq;
	
}
