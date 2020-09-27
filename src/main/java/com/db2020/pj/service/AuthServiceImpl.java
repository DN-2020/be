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
				 	            .user_id(user.getUser_id())
				 	            .user_password(passwordEncoder.encode(user.getUser_password()))
				 	            .user_name(user.getUser_name())
				 	            .user_tel(user.getUser_tel())
				 	            .user_postNum(user.getUser_postNum())
				 	            .user_address(user.getUser_address())
				 	            .user_detail_address(user.getUser_detail_address())
				                .roles(Collections.singletonList("ROLE_USER")).build());
	}
    
}