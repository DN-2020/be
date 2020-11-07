package com.db2020.pj.service;

import java.util.HashMap;
import java.util.List;

import com.db2020.pj.entity.Emp;

public interface EmpServcie {

    int empInsert(Emp emp);

    List<Emp> companyEmpList(int company_seq);

    Emp empDetail(HashMap<String, Integer> map);

    int empUpdate(Emp emp);

}
