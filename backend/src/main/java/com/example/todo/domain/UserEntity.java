package com.example.todo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="todo_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserEntity {
	
	@Id
	private String userId;
	
	private String userName;
	private String password;

}
