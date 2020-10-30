package com.db2020.pj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db2020.pj.entity.Emp;
import com.db2020.pj.repository.EmpRepository;

@Service
public class EmpServiceImpl implements EmpServcie{

	@Autowired
	EmpRepository empRepository;
	
	@Override
	public int empInsert(Emp emp) {
		// TODO Auto-generated method stub
		return empRepository.empInsert(emp);
	}

	@Override
	public List<Emp> companyEmpList(int company_seq) {
		// TODO Auto-generated method stub
		return empRepository.companyEmpList(company_seq);
	}

	@Override
	public Emp empDetail(HashMap<String, Integer> map) {
		// TODO Auto-generated method stub
		return empRepository.empDetail(map);
	}

	@Override
	public int empUpdate(Emp emp) {
		// TODO Auto-generated method stub
		return empRepository.empUpdate(emp);
	}

}
