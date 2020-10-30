package com.db2020.pj.repository;

import java.util.List;

import com.db2020.pj.entity.Dept;

public interface DeptRepository {

	List<Dept> deptList();
	
	int deptInsert(Dept dept);
	
	int topDeptInsert(Dept dept);
	
	Dept deptDetailList(int dept_seq);
	
	int deptUpdate(Dept dept);
	
	int deptDelete(int dept_seq);
	
}
