package com.db2020.pj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.db2020.pj.entity.Customer;
import com.db2020.pj.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

//		Customer user = userRepository.findById(email);
//		if (user == null) {
//			throw new UsernameNotFoundException(email + " : 사용자 존재하지 않음");
//		}

		return null;
	}

}
