package com.db2020.pj.repository;

import java.util.Map;

import com.db2020.pj.entity.Customer;

import javassist.bytecode.DuplicateMemberException;

public interface UserRepository {

	public String findById(String email);
	
	public Customer findUserInfo(String email);
	
	public Customer findUserInfo(Map<String, String> loginMap);
	
	public void signUp(Customer user);

	public void reviseUserInfo(Customer user);
	
	public void removeUser(String customer_email);

}
