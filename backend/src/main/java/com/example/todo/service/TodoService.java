package com.example.todo.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.todo.domain.TodoEntity;
import com.example.todo.domain.TodoRequest;
import com.example.todo.exception.CustomException;
import com.example.todo.exception.ErrorCode;
import com.example.todo.repository.TodoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
	
	private final TodoRepository todoRepository;

	public String write(TodoRequest dto) {
		String userId = SecurityContextHolder.getContext()
							.getAuthentication().getName();
		
		System.out.println("subject:: "+dto.getSubject());
		System.out.println("body:: "+dto.getBody());
		System.out.println("userId:: "+userId);
		for (int i = 0; i < dto.getFiles().size(); i++) {
			System.out.println("fileName:: "+dto.getFiles().get(i).getOriginalFilename());
		}
		
		TodoEntity newTodo = TodoEntity.builder()
								.subject(dto.getSubject())
								.body(dto.getBody())
								.userId(userId)
								.build();
		
	//	todoRepository.save(newTodo);
		return "SUCCESS";
	}

	public List<TodoEntity> getList(String userId) {
		return todoRepository.findAllByUserIdOrderByIdDesc(userId);
	}

	@Transactional
	public boolean updateTodo(TodoRequest dto) {
		
		TodoEntity todo = todoRepository.findById(dto.getChangeId())
									.orElseThrow(() -> 
										new CustomException(ErrorCode.NOT_FOUND, "Todo Item을 찾지 못했습니다.")
									);
		
		todo.setCompleted(dto.getChangeCompleted());
		return true;
	}

}
