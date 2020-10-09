package com.db2020.pj.entity;

import com.db2020.pj.entity.Customer.CustomerBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
public class LoginDTO {
	
	private long customer_seq;
    private String customer_email;
    private String customer_nm;
    private String customer_tel;
    private int customer_post;
    private String customer_address;
    private String customer_detail_address;
    private String access_token;
    private String customer_role;
    
}
