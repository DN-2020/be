package com.db2020.pj.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.db2020.pj.config.redis.RedisUtil;
import com.db2020.pj.entity.Customer;
import com.db2020.pj.exception.custom.CUserExistException;
import com.db2020.pj.exception.custom.CUserNotException;
import com.db2020.pj.exception.custom.CUserPWException;
import com.db2020.pj.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	@Autowired
    private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    private RedisUtil redisUtil;
    
    @Override
    public Customer loginUser(String email, String pw) throws Exception{
//        Customer user = userRepository.findById(email);
//        if(user == null) 
//        	throw new CUserNotException();
//        if(!passwordEncoder.matches(pw, user.getCustomer_pw()))
//            throw new CUserPWException();
        return null;
    }

	@Override
	public void signUp(Customer user) {
		String email = userRepository.findById(user.getCustomer_email());
		if(user.getCustomer_email().equals(email)) {
			throw new CUserExistException();
		}
		else {
			userRepository.signUp(Customer.builder()
	 	            .customer_email(user.getCustomer_email())
	 	            .customer_pw(passwordEncoder.encode(user.getCustomer_pw()))
	 	            .customer_nm(user.getCustomer_nm())
	 	            .customer_tel(user.getCustomer_tel())
	 	            .customer_post(user.getCustomer_post())
	 	            .customer_address(user.getCustomer_address())
	 	            .customer_address_detail(user.getCustomer_address_detail())
	                .roles(Collections.singletonList("ROLE_USER")).build());
		}
	}
}