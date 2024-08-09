package com.example.todo.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Component
public class FileUtil {

	@Value("${file.upload-dir}")
	private String uploadPath;
	
	private static Path fileStorageLocation;
	
    @PostConstruct
    public void init() {
        // 인스턴스 필드 값을 사용하여 정적 필드를 초기화
        fileStorageLocation = Paths.get(uploadPath).toAbsolutePath().normalize();

        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }	

    
    public static String storeFile(MultipartFile file) {
        // 원본 파일 이름을 가져옴
        String fileName = file.getOriginalFilename();

        try {
            // 타겟 위치 결정
            Path targetLocation = fileStorageLocation.resolve(fileName);
            // 파일 저장
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    
    public static Path loadFileAsResource(String fileName) {
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            if(Files.exists(filePath)) {
                return filePath;
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (Exception ex) {
            throw new RuntimeException("File not found " + fileName, ex);
        }
    }    

}
