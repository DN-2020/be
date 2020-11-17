package com.db2020.pj.service;

import java.util.Map;

import com.db2020.pj.entity.Customer;
import com.db2020.pj.entity.Emp;
import com.db2020.pj.entity.EmpDTO;

public interface AuthService {

	Customer loginUser(Map<String, String> loginMap) throws Exception;

	Emp loginEmp(Emp empDTO) throws Exception;

	void signUp(Customer user) throws Exception;

}
