package com.db2020.pj.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.db2020.pj.entity.Customer;
import com.db2020.pj.repository.UserRepository;
import com.db2020.pj.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Customer userInfo(String email) {
	
		Customer user = userRepository.findUserInfo(email);
		
		return user;
	}

	@Override
	public void userInfo(Customer user) {

		userRepository.reviseUserInfo(Customer.builder()
				.customer_seq(user.getCustomer_seq())
				.customer_email(user.getCustomer_email())
				.customer_pw(passwordEncoder.encode(user.getCustomer_pw()))
				.customer_nm(user.getCustomer_nm())
				.customer_tel(user.getCustomer_tel())
				.customer_post(user.getCustomer_post())
				.customer_address(user.getCustomer_address())
				.customer_detail_address(user.getCustomer_detail_address()).build());
		
	}

	@Override
	public void removeUser(String customer_email) {
		
		userRepository.removeUser(customer_email);
	}

	@Override
	public int selectUserSeq(String email) {
		return userRepository.customerSeq(email);
	}
}
