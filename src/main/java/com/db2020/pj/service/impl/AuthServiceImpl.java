package com.db2020.pj.service.impl;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.db2020.pj.config.redis.RedisUtil;
import com.db2020.pj.entity.Customer;
import com.db2020.pj.exception.custom.CUserExistException;
import com.db2020.pj.exception.custom.CUserNotException;
import com.db2020.pj.exception.custom.CUserPWException;
import com.db2020.pj.repository.UserRepository;
import com.db2020.pj.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public Customer loginUser(Map<String, String> loginMap) throws Exception {
		Customer user = userRepository.findUserInfo(loginMap);
		if (user.getCustomer_email() == null)
			throw new CUserNotException();
		if (!passwordEncoder.matches(loginMap.get("customer_pw"), user.getCustomer_pw()))
			throw new CUserPWException();
		return user;
	}

	@Override
	public void signUp(Customer user) {
		String email = userRepository.findById(user.getCustomer_email());
		if (user.getCustomer_email().equals(email)) {
			throw new CUserExistException();
		} else {
			userRepository.signUp(Customer.builder()
					.customer_email(user.getCustomer_email())
					.customer_pw(passwordEncoder.encode(user.getCustomer_pw()))
					.customer_nm(user.getCustomer_nm())
					.customer_tel(user.getCustomer_tel())
					.customer_post(user.getCustomer_post())
					.customer_address(user.getCustomer_address())
					.customer_detail_address(user.getCustomer_detail_address())
					.customer_role("ROLE_USER").build());
		}
	}

}