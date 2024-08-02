package com.example.todo.service;

import org.springframework.stereotype.Service;

import com.example.todo.domain.UserEntity;
import com.example.todo.domain.UserRequest;
import com.example.todo.exception.CustomException;
import com.example.todo.exception.ErrorCode;
import com.example.todo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
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
	

}
