package com.db2020.pj.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db2020.pj.entity.Company;
import com.db2020.pj.model.CommonResult;
import com.db2020.pj.model.Response;
import com.db2020.pj.service.CompanyService;

@RestController
@RequestMapping(value = "v1")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@GetMapping(value = "company")
	public Response companyList(HttpServletRequest req, HttpServletResponse res) {
		if (companyService.companyList().isEmpty()) {
			return new Response("400", "해당 목록이 비어있습니다.", null);
		} else {
			return new Response("200", "회사목록조회 결과에 성공하였습니다.", companyService.companyList());
		}
	}

	@PostMapping(value = "company")
	public CommonResult companyInsert(HttpServletRequest req, HttpServletResponse res, @RequestBody Company company) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		company.setCompany_createAt(time1);
		companyService.companyInsert(company);
		CommonResult commonResult = new CommonResult();
		commonResult.setCode(200);
		commonResult.setMsg("회사등록 결과에 성공하였습니다.");
		return commonResult;
	}

	@GetMapping(value = "company/{company_seq}")
	public Response companyDetailList(HttpServletRequest req, HttpServletResponse res,
			@PathVariable(value = "company_seq") int company_seq) {
		if (companyService.companyDetailList(company_seq).isEmpty()) {
			return new Response("400", "해당 목록이 비어있습니다.", null);
		} else {
			return new Response("200", "회사상세조회 결과에 성공하였습니다.", companyService.companyDetailList(company_seq));
		}
	}

	@RequestMapping(value = "company/{company_seq}", method = RequestMethod.PUT)
	public CommonResult CompanyUpdate(HttpServletRequest req, HttpServletResponse res, @RequestBody Company company,
			@PathVariable(value = "company_seq") int company_seq) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		company.setCompany_updateAt(time1);
		company.setCompany_seq(company_seq);
		companyService.companyUpdate(company);
		CommonResult result = new CommonResult();
		result.setCode(200);
		result.setMsg("회사수정 결과에 성공하였습니다.");

		return result;
	}

	@RequestMapping(value = "/company/{company_seq}", method = RequestMethod.DELETE)
	public CommonResult companyDelete(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("company_seq") int company_seq) {

		companyService.companyDelete(company_seq);
		CommonResult result = new CommonResult();
		result.setCode(200);
		result.setMsg("회사삭제 결과에 성공하였습니다.");

		return result;
	}
}
