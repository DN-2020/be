package com.db2020.pj.repository;

import java.util.Map;

import com.db2020.pj.entity.Emp;
import com.db2020.pj.entity.EmpDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Customer findUserInfo(String email) {

		Customer user = sqlSession.selectOne("customer.info", email);
		return user;
	}

	@Override
	public Customer findUserInfo(Map<String, String> loginMap) {
		return sqlSession.selectOne("customer.signIn", loginMap);
	}

	@Override
	public void signUp(Customer customer) {
		sqlSession.insert("customer.signUp", customer);
//		sqlSession.insert("customer.signRole", customer.getRoles());	
	}

	@Override
	public void reviseUserInfo(Customer customer) {
		sqlSession.update("customer.reviseInfo", customer);
	}

	@Override
	public void removeUser(String customer_email) {
		sqlSession.delete("customer.remove", customer_email);
	}

	@Override
	public int customerSeq(String email) {
		return sqlSession.selectOne("customer.customerSeq", email);
	}
}
