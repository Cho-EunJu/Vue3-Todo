package com.example.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoRequest {
	
	private Long changeId;
	private String changeCompleted;
	
	private String subject;
	private String body;
	
	private String userId;

}
