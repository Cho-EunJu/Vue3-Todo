package com.example.todo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> appExceptionHandler(CustomException e){
		return ResponseEntity.status(e.getErrorCode().getHttpStatus())
				.body(e.getErrorCode().getHttpStatus().name() + " : " +e.getMessage());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e){
		e.printStackTrace();
		return ResponseEntity.status(ErrorCode.RUNTIME.getHttpStatus())
				.body(e.getMessage());
	}	
	
}
