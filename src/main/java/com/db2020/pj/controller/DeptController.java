package com.db2020.pj.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.db2020.pj.entity.Dept;
import com.db2020.pj.model.CommonResult;
import com.db2020.pj.model.Response;
import com.db2020.pj.service.DeptService;

@RestController
@RequestMapping(value = "v1")
public class DeptController {

	@Autowired
	DeptService deptService;

	@GetMapping(value = "dept")
	public Response deptList(HttpServletRequest req, HttpServletResponse res) {
		if (deptService.deptList().isEmpty()) {
			return new Response("400", "해당 목록이 비어있습니다.", null);
		} else {
			return new Response("200", "사원조회 결과에 성공하였습니다.", deptService.deptList());
		}
	}

	@PostMapping(value = "dept")
	public CommonResult deptInsert(HttpServletRequest req, HttpServletResponse res, @RequestBody Dept dept) {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		dept.setDept_createAt(time1);

		if (dept.getT_dept_seq() == 0) {
			deptService.deptInsert(dept); // 상위부서 포함x
		} else {
			deptService.topDeptInsert(dept); // 상위부서 포함 o
		}
		CommonResult commonResult = new CommonResult();
		commonResult.setCode(200);
		commonResult.setMsg("부서등록 결과에 성공하였습니다.");

		return commonResult;

	}

	@GetMapping(value = "dept/{dept_seq}")
	public Response deptDetailList(HttpServletRequest req, HttpServletResponse res,
			@PathVariable(value = "dept_seq") int dept_seq) {
		if(deptService.deptDetailList(dept_seq)==null) {
			return new Response("400", "해당 목록이 비어있습니다.", null);
		}else {
		return new Response("200", "부서상세조회 결과에 성공하였습니다.", deptService.deptDetailList(dept_seq));
		}
	}

	@RequestMapping(value = "dept/{dept_seq}", method = RequestMethod.PUT)
	public CommonResult deptUpdate(HttpServletRequest req, HttpServletResponse res, @RequestBody Dept dept,
			@PathVariable(value = "dept_seq") int dept_seq) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		dept.setDept_updateAt(time1);
		dept.setDept_seq(dept_seq);
		deptService.deptUpdate(dept);
		CommonResult result = new CommonResult();
		result.setCode(200);
		result.setMsg("부서수정 결과에 성공하였습니다.");

		return result;
	}

	@RequestMapping(value = "/dept/{dept_seq}", method = RequestMethod.DELETE)
	public CommonResult deptDelete(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("dept_seq") int dept_seq) {

		deptService.deptDelete(dept_seq);
		CommonResult result = new CommonResult();
		result.setCode(200);
		result.setMsg("부서삭제 결과에 성공하였습니다.");

		return result;
	}

}
