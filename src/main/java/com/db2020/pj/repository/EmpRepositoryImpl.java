package com.db2020.pj.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db2020.pj.entity.Emp;

@Repository
public class EmpRepositoryImpl implements EmpRepository {

    @Autowired
    SqlSession sqlSession;

    @Override
    public void empInsert(Emp emp) {
        // TODO Auto-generated method stub
        sqlSession.insert("empDAO.empInsert", emp);
    }

    @Override
    public Emp findEmpInfo(Emp emp) {

        System.out.println(emp.toString());
        Emp param = sqlSession.selectOne("empDAO.info", emp.getEmp_email());
        return param;
    }

    @Override
    public Emp findEmpInfo(String emp_email) {

        Emp param = sqlSession.selectOne("empDAO.info1", emp_email);
        return param;
    }

    @Override
    public List<Emp> companyEmpList(int company_seq) {
        // TODO Auto-generated method stub
        return sqlSession.selectList("empDAO.companyEmpList", company_seq);
    }

    @Override
    public Emp empDetail(HashMap<String, Integer> map) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne("empDAO.empDetail", map);
    }

    @Override
    public int empUpdate(Emp emp) {
        // TODO Auto-generated method stub
        return sqlSession.update("empDAO.empUpdate", emp);
    }


}
