package com.db2020.pj.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.db2020.pj.config.redis.RedisUtil;
import com.db2020.pj.entity.User;
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
    public User loginUser(String id, String password) throws Exception{
        User user = userRepository.findById(id);
        if(user==null) 
        	throw new CUserNotException();
        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new CUserPWException();
        return user;
    }

	@Override
	public void signUp(User user) {
		
		userRepository.save(User.builder()
				 	            .id(user.getId())
				 	            .password(passwordEncoder.encode(user.getPassword()))
				 	            .name(user.getName())
				                .roles(Collections.singletonList("ROLE_USER")).build());
	}
    
}
