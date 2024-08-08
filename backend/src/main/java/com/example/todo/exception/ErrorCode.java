package com.example.todo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	USER_DUPILCATED(HttpStatus.CONFLICT, ""),
	NOT_FOUND(HttpStatus.NOT_FOUND, ""),
	RUNTIME(HttpStatus.SEE_OTHER, ""),
	JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "");
	
	private HttpStatus httpStatus;
	private String message;
}
