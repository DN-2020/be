package com.db2020.pj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db2020.pj.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findById(String username);
}
