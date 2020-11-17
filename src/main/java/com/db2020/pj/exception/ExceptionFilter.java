package com.db2020.pj.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.db2020.pj.exception.custom.CAuthenticationEntryPointException;
import com.db2020.pj.model.CommonResult;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/exception")
public class ExceptionFilter {
	
	@GetMapping(value = "/entrypoint")
	public CommonResult entrypointException() {
		throw new CAuthenticationEntryPointException();
	}
	
	@GetMapping(value = "/accessdenied")
	public CommonResult accessdeniedException() {
		throw new AccessDeniedException("");
	}
}

