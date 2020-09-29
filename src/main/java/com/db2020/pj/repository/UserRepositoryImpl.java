package com.db2020.pj.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.db2020.pj.entity.Customer;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String findById(String email) {
		String result = sqlSession.selectOne("customer.selectId", email);
		return result;
	}

	@Override
	public void signUp(Customer customer) {
		sqlSession.insert("customer.insertCustomer", customer);
	}

}
