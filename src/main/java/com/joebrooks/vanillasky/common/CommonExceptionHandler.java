package com.joebrooks.vanillasky.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.joebrooks.vanillasky")
public class CommonExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException() {
		return "error";
	}

	@ExceptionHandler(IllegalStateException.class)
	public String handleIllegalException() {
		return "error";
	}

}
