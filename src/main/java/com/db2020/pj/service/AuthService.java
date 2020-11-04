package com.db2020.pj.service;

import java.util.Map;

import com.db2020.pj.entity.Customer;

public interface AuthService {

	Customer loginUser(Map<String, String> loginMap) throws Exception;
	
	void signUp(Customer user) throws Exception;

}
