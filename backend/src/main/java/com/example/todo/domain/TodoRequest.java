package com.example.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoRequest {
	
	private String subject;
	private String body;
	
	private String userId;

}
