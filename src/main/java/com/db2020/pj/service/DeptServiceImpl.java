package com.db2020.pj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db2020.pj.entity.Dept;
import com.db2020.pj.repository.DeptRepository;

@Service
public class DeptServiceImpl implements DeptService{

	@Autowired
	DeptRepository deptRepository;
	
	@Override
	public List<Dept> deptList() {
		// TODO Auto-generated method stub
		return deptRepository.deptList();
	}

	@Override
	public int deptInsert(Dept dept) {
		// TODO Auto-generated method stub
		return deptRepository.deptInsert(dept);
	}

	@Override
	public int topDeptInsert(Dept dept) {
		// TODO Auto-generated method stub
		return deptRepository.topDeptInsert(dept);
	}

	@Override
	public Dept deptDetailList(int dept_seq) {
		// TODO Auto-generated method stub
		return deptRepository.deptDetailList(dept_seq);
	}

	@Override
	public int deptUpdate(Dept dept) {
		// TODO Auto-generated method stub
		return deptRepository.deptUpdate(dept);
	}

	@Override
	public int deptDelete(int dept_seq) {
		// TODO Auto-generated method stub
		return deptRepository.deptDelete(dept_seq);
	}

}
