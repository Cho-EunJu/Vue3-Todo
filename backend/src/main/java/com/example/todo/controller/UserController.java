package com.example.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.domain.UserRequest;
import com.example.todo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/check-id")
	public ResponseEntity<?> idDupCheck(@RequestBody String userId){
		return ResponseEntity.ok().body(userService.idDupCheck(userId));
	}
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody UserRequest dto){
		return ResponseEntity.ok().body(userService.signUp(dto));
	}

}
