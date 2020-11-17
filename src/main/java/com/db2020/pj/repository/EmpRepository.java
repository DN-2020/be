package com.db2020.pj.repository;

import java.util.HashMap;
import java.util.List;

import com.db2020.pj.entity.Emp;

public interface EmpRepository {

    void empInsert(Emp emp);

    Emp findEmpInfo(Emp emp);

    Emp findEmpInfo(String emp_email);

    List<Emp> companyEmpList(int company_seq);

    Emp empDetail(HashMap<String, Integer> map);

    int empUpdate(Emp emp);

}
