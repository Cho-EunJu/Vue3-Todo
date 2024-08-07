package com.example.todo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	USER_DUPILCATED(HttpStatus.CONFLICT, ""),
	USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, ""),
	RUNTIME(HttpStatus.SEE_OTHER, ""),
	JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "");
	
	private HttpStatus httpStatus;
	private String message;
}
