package com.db2020.pj.service;

import com.db2020.pj.entity.Customer;

public interface AuthService {

	Customer loginUser(String id, String password) throws Exception;
	
	void signUp(Customer user) throws Exception;

}
