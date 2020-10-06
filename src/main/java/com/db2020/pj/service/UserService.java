package com.db2020.pj.service;

import com.db2020.pj.entity.Customer;

public interface UserService {

	public Customer userInfo(String email);

	public void userInfo(Customer customer);
	
	public void removeUser(String customer_email);

	public int selectUserSeq(String email);
}
