package com.db2020.pj.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.db2020.pj.exception.custom.CAuthenticationEntryPointException;
import com.db2020.pj.exception.custom.CUserExistException;
import com.db2020.pj.exception.custom.CUserNotException;
import com.db2020.pj.exception.custom.CUserPWException;
import com.db2020.pj.model.CommonResult;
import com.db2020.pj.service.ResponseService;

import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionController {
	
	private final ResponseService responseService;
	
	private final MessageSource messageSource;
	
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public CommonResult defaulException(HttpServletRequest request, Exception e) {
//		
//		return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg"));
//	}
	
	@ExceptionHandler(CUserNotException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResult userNotException(HttpServletRequest request, CUserNotException e) {
		
		return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")), getMessage("userNotFound.msg"));
	}
	
	@ExceptionHandler(CUserPWException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResult userPWException(HttpServletRequest request, CUserPWException e) {
		
		return responseService.getFailResult(Integer.valueOf(getMessage("userPwWrong.code")), getMessage("userPwWrong.msg"));
	}
	
	@ExceptionHandler(CUserExistException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResult userExistException(HttpServletRequest request, CUserExistException e) {
		
		return responseService.getFailResult(Integer.valueOf(getMessage("userExist.code")), getMessage("userExist.msg"));
	}
	
	@ExceptionHandler(CAuthenticationEntryPointException.class)
	public CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
		System.out.println("rrrr");
	    return responseService.getFailResult(Integer.valueOf(getMessage("entryPointException.code")), getMessage("entryPointException.msg"));
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public CommonResult accessDeniedException(HttpServletRequest request, AccessDeniedException e) {
	        return responseService.getFailResult(Integer.valueOf(getMessage("accessDenied.code")), getMessage("accessDenied.msg"));
	}
	
	// code정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }
    // code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회합니다.
    // 첫 번째 인자는 .yml 파일에서 설정한 code값, 두 번째 인자는 ???, 세번째 인자는 언어 설정
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
