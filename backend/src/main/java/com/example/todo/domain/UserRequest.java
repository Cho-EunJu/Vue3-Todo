package com.example.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserRequest {
	
	private String userId;
	private String userName;
	private String password;

}
