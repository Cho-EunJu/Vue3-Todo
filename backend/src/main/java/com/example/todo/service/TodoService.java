package com.example.todo.service;

import org.springframework.stereotype.Service;

import com.example.todo.domain.TodoEntity;
import com.example.todo.domain.TodoRequest;
import com.example.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
	
	private final TodoRepository todoRepository;

	public String write(TodoRequest dto) {
		TodoEntity newTodo = TodoEntity.builder()
								.subject(dto.getSubject())
								.body(dto.getBody())
								.userId(dto.getUserId())
								.build();
		todoRepository.save(newTodo);
		
		return "SUCCESS";
	}

}
