package com.db2020.pj.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db2020.pj.entity.Dept;

@Repository
public class DeptRepositoryImpl implements DeptRepository {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Dept> deptList() {
        // TODO Auto-generated method stub
        return sqlSession.selectList("deptDAO.deptList");
    }

    @Override
    public int deptInsert(Dept dept) {
        // TODO Auto-generated method stub
        return sqlSession.insert("deptDAO.deptInsert", dept);
    }

    @Override
    public int topDeptInsert(Dept dept) {
        // TODO Auto-generated method stub
        return sqlSession.insert("deptDAO.topDeptInsert", dept);
    }

    @Override
    public Dept deptDetailList(int dept_seq) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne("deptDAO.deptDetailList", dept_seq);
    }

    @Override
    public int deptUpdate(Dept dept) {
        // TODO Auto-generated method stub
        return sqlSession.update("deptDAO.deptUpdate", dept);
    }

    @Override
    public int deptDelete(int dept_seq) {
        // TODO Auto-generated method stub
        return sqlSession.delete("deptDAO.deptDelete", dept_seq);
    }

}
