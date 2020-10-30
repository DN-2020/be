package com.db2020.pj.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db2020.pj.entity.Emp;
import com.db2020.pj.model.CommonResult;
import com.db2020.pj.model.Response;
import com.db2020.pj.service.EmpServcie;

@RestController
@RequestMapping(value = "v1")
public class EmpController {

	@Autowired
	EmpServcie empService;
	
	@PostMapping(value = "company/signup") // 사원 가입
	public CommonResult empInsert(HttpServletRequest req, HttpServletResponse res,@RequestBody Emp emp) {
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		emp.setEmp_createAt(time1);
		
		empService.empInsert(emp);
		
		CommonResult commonResult = new CommonResult();
		commonResult.setCode(200);
		commonResult.setMsg("success");
		
		return commonResult;
	}
	
	@GetMapping(value = "company/{company_seq}/emp") //회사별 사원정보 조회
	public Response companyEmpList(HttpServletRequest req, HttpServletResponse res,@PathVariable(value = "company_seq") int company_seq) {
		
		return new Response("200","success",empService.companyEmpList(company_seq));
	}
	
	@GetMapping(value = "company/{company_seq}/emp/{emp_seq}") //회사별 사원정보 조회
	public Response empDetail(HttpServletRequest req, HttpServletResponse res,@PathVariable(value = "company_seq") int company_seq,@PathVariable(value = "emp_seq") int emp_seq) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("company_seq",company_seq);
		map.put("emp_seq",emp_seq);
		return new Response("200","success",empService.empDetail(map));
	}
	
	@RequestMapping(value = "company/{company_seq}/emp/{emp_seq}",method = RequestMethod.PUT)
	public CommonResult empUpdate(HttpServletRequest req, HttpServletResponse res,@RequestBody Emp emp) {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		emp.setEmp_updateAt(time1);
		empService.empUpdate(emp);
		CommonResult result = new CommonResult();
		result.setCode(200);
		result.setMsg("success");
		
		return result;
	}
	
}