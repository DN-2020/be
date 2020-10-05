package com.db2020.pj.service;

import com.db2020.pj.entity.User;

public interface AuthService {

	User loginUser(String id, String password) throws Exception;
	
	void signUp(User user) throws Exception;
}
