package com.example.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.todo.domain.TodoRequest;
import com.example.todo.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
	
	private final TodoService todoService;
	
	
	@PostMapping("/todo")
	public ResponseEntity<?> write(@ModelAttribute TodoRequest dto){
		
		return ResponseEntity.ok().body(todoService.write(dto));
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> todoList(Authentication auth){
		String userId = auth.getName();
		return ResponseEntity.ok().body(todoService.getList(userId));
	}
	
	@PutMapping("/todo")
	public ResponseEntity<?> update(@RequestBody TodoRequest dto){
		return ResponseEntity.ok().body(todoService.updateTodo(dto));
	}
	

}
