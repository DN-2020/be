package com.db2020.pj.service;

import java.util.List;

import com.db2020.pj.entity.Company;

public interface CompanyService {

    List<Company> companyList();

    int companyInsert(Company company);

    List<Company> companyDetailList(int company_seq);

    int companyUpdate(Company company);

    int companyDelete(int company_seq);
}
