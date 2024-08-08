package com.example.todo.service;

import org.springframework.stereotype.Service;

import com.example.todo.domain.UserEntity;
import com.example.todo.domain.UserRequest;
import com.example.todo.exception.CustomException;
import com.example.todo.exception.ErrorCode;
import com.example.todo.repository.UserRepository;
import com.example.todo.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;

	public boolean idDupCheck(String userId) {
		
		userRepository.findByUserId(userId)
		.ifPresent(user -> {
			throw new CustomException(ErrorCode.USER_DUPILCATED, "중복된 ID 입니다.");
		});
		
		return true;
	}

	public boolean signUp(UserRequest dto) {
		
		UserEntity user = UserEntity.builder()
							.userId(dto.getUserId())
							.userName(dto.getUserName())
							.password(encoder.encode(dto.getPassword()))
							.build();
		
		userRepository.save(user);
		return true;
	}

	public Map<String, String> login(UserRequest dto) {
		
		Map<String, String> result = new HashMap<String, String>();
		
		
		UserEntity user =  userRepository.findByUserId(dto.getUserId())
							.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "잘못된 ID또는 PW 입니다."));
		
		if(!encoder.matches(dto.getPassword(), user.getPassword())) {
			throw new CustomException(ErrorCode.NOT_FOUND, "잘못된 ID또는 PW 입니다.");
		}
		
		
		String token = JwtUtil.generateToken(user.getUserId());
		
		result.put("token", token);
		result.put("userName", user.getUserName());
		
		return result;
	}
	

}
