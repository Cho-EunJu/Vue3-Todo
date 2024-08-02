package com.example.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequest {
	
	private String userId;
	private String userName;
	private String password;

}
