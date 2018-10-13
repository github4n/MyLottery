package com.csy.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//请求参数异常处理
@ControllerAdvice   //表示这个类是控制器增强
public class RestExceptionHandler {
	
	/**
	 * 这个方法处理异常信息
	 */
	@ExceptionHandler({Exception.class})
	private void RestExceptionHandler() {
		
	}
}
