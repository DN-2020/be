package com.db2020.pj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db2020.pj.entity.Company;
import com.db2020.pj.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	CompanyRepository companyRepository;

	@Override
	public List<Company> companyList() {
		// TODO Auto-generated method stub
		return companyRepository.companyList();
	}

	@Override
	public int companyInsert(Company company) {
		// TODO Auto-generated method stub
		return companyRepository.companyInsert(company);
	}

	@Override
	public List<Company> companyDetailList(int company_seq) {
		// TODO Auto-generated method stub
		return companyRepository.companyDetailList(company_seq);
	}

	@Override
	public int companyUpdate(Company company) {
		// TODO Auto-generated method stub
		return companyRepository.companyUpdate(company);
	}

	@Override
	public int companyDelete(int company_seq) {
		// TODO Auto-generated method stub
		return companyRepository.companyDelete(company_seq);
	}

	
}
