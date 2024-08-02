package com.example.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.domain.TodoRequest;
import com.example.todo.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
	
	private final TodoService todoService;
	
	
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody TodoRequest dto){
		return ResponseEntity.ok().body(todoService.write(dto));
	}
	

}
