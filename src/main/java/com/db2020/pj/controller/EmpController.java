package com.db2020.pj.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db2020.pj.config.jwt.JwtUtil;
import com.db2020.pj.config.redis.RedisUtil;
import com.db2020.pj.entity.Customer;
import com.db2020.pj.entity.EmpDTO;
import com.db2020.pj.entity.LoginDTO;
import com.db2020.pj.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
@RequestMapping(value = "v1")
public class EmpController {

	@Autowired
	EmpServcie empService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthService authService;
	@Autowired
	private RedisUtil redisUtil;

	@PostMapping(value = "company/signup") // 사원 가입
	public CommonResult empInsert(HttpServletRequest req, HttpServletResponse res, @RequestBody Emp emp) {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		emp.setEmp_createAt(time1);

		empService.empInsert(emp);

		CommonResult commonResult = new CommonResult();
		commonResult.setCode(200);
		commonResult.setMsg("사원가입 결과에 성공하였습니다.");

		return commonResult;
	}

	@PostMapping(value = "/company/signin")
	public Response signin(HttpServletRequest req, HttpServletResponse res, @RequestBody Emp emp) throws Exception {

		System.out.println(emp.toString());
		Emp param = authService.loginEmp(emp);

		final String accesstoken = jwtUtil.generateEmpToken(param);
		final String refreshtoken = jwtUtil.generateRefreshEmpToken(param);

		redisUtil.setDataExpire(refreshtoken, emp.getEmp_email(), JwtUtil.TOKEN_VALIDATION_SECOND);
//
		res.addHeader("Authorization", accesstoken);
		res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
//		res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "SET_COOKIE");
		res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:3000");
		System.out.println(accesstoken);

		EmpDTO login = new EmpDTO(param.getEmp_seq(),
				param.getEmp_email(),
				param.getEmp_pw(),
				param.getEmp_nm(),
				param.getEmp_joinDate(),
				param.getEmp_isUsed(),
				param.getEmp_role(),
				param.getCompany_seq(),
				param.getCompany_nm(),
				param.getDept_seq(),
				accesstoken
//									  refreshToken.getValue()
		);

		return new Response("200", "로그인을 성공적으로 하였습니다.", login);
	}


	@GetMapping(value = "company/{company_seq}/emp") // 회사별 사원정보 조회
	public Response companyEmpList(HttpServletRequest req, HttpServletResponse res,
			@PathVariable(value = "company_seq") int company_seq) {
		if (empService.companyEmpList(company_seq).isEmpty()) {
			return new Response("400", "해당 목록이 비어있습니다.", null);
		} else {
			return new Response("200", "회사별사원정보조회 결과에 성공하였습니다.", empService.companyEmpList(company_seq));
		}
	}

	@GetMapping(value = "company/{company_seq}/emp/{emp_seq}") // 회사별 사원상세정보 조회
	public Response empDetail(HttpServletRequest req, HttpServletResponse res,
			@PathVariable(value = "company_seq") int company_seq, @PathVariable(value = "emp_seq") int emp_seq) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("company_seq", company_seq);
		map.put("emp_seq", emp_seq);
		if (empService.empDetail(map)==null) {
			return new Response("400", "해당 목록이 비어있습니다.", null);
		} else {
			return new Response("200", "회사별사원상세정보조회 결과에 성공하였습니다.", empService.empDetail(map));
		}
	}

	@RequestMapping(value = "company/{company_seq}/emp/{emp_seq}", method = RequestMethod.PUT)
	public CommonResult empUpdate(HttpServletRequest req, HttpServletResponse res, @RequestBody Emp emp,
			@PathVariable(value = "company_seq") int company_seq, @PathVariable(value = "emp_seq") int emp_seq) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		emp.setEmp_updateAt(time1);
		emp.setCompany_seq(company_seq);
		emp.setEmp_seq(emp_seq);
		empService.empUpdate(emp);
		CommonResult result = new CommonResult();
		result.setCode(200);
		result.setMsg("사원수정 결과에 성공하였습니다.");

		return result;
	}

}
