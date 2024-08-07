package com.example.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.domain.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long>{
	List<TodoEntity> findAllByUserIdOrderByIdDesc(String userId);
}
