package com.example.todo.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoRequest {
	
	private Long changeId;
	private String changeCompleted;
	
	private String subject;
	private String body;
	
	private String userId;
	
	private List<MultipartFile> files;

}
