package com.db2020.pj.service.detail;

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

//		 유저 전체 정보를 가져오는 구문을 만들어야 한다.
		Customer user = userRepository.findUserInfo(email);
		if (user == null) {
			throw new UsernameNotFoundException(email + " : 사용자 존재하지 않음");
		}
		return user;
	}

}
