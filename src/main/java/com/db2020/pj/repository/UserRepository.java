package com.db2020.pj.repository;

import com.db2020.pj.entity.Customer;

import javassist.bytecode.DuplicateMemberException;

public interface UserRepository {

	public String findById(String email);
	
	public void signUp(Customer customer);

}
