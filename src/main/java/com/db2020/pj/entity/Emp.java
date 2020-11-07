package com.db2020.pj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Emp {

    private int emp_seq;
    private String emp_nm;
    private String emp_account;
    private String emp_pw;
    private String emp_joinDate;
    private String emp_address;
    private String emp_email;
    private String emp_tel;
    private String emp_phone;
    private String emp_createAt;
    private String emp_updateAt;
    private String emp_isUsed;
    private String emp_vacationDate;
    private int dept_seq;
    private int company_seq;

}
