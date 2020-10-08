package com.db2020.pj.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db2020.pj.entity.Company;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Company> companyList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("companyDAO.companyList");
	}

	@Override
	public int companyInsert(Company company) {
		// TODO Auto-generated method stub
		return sqlSession.insert("companyDAO.companyInsert",company);
	}

	@Override
	public List<Company> companyDetailList(int company_seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("companyDAO.companyDetailList",company_seq);
	}

	@Override
	public int companyUpdate(Company company) {
		// TODO Auto-generated method stub
		return sqlSession.update("companyDAO.companyUpdate",company);
	}

	@Override
	public int companyDelete(int company_seq) {
		// TODO Auto-generated method stub
		return sqlSession.delete("companyDAO.companyDelete",company_seq);
	}
}
