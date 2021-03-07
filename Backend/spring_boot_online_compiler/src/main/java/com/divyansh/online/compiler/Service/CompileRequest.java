package com.divyansh.online.compiler.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CompileRequest {
	
	@Autowired
	EntryPointRequest entryPointRequest;
	
	public ResponseEntity<Object> compile(MultipartFile codeFile, MultipartFile outpFile, 
			MultipartFile inputFile, int timeLimit, int storageLimit, String language){
		String folder = "";
		String file = "";
		
		if(language == "c") {
			folder = "program_c";
			file = "main.c";
			entryPointRequest.entrypointCFile(codeFile.getOriginalFilename(), inputFile, timeLimit, storageLimit);
		}
		else if(language == "cpp") {
			folder = "program_cpp";
			file = "main.cpp";
			entryPointRequest.entrypointCppFile(codeFile.getOriginalFilename(), inputFile, timeLimit, storageLimit);
		}
		else if(language == "java") {
			folder = "program_java";
			file = "main.java";
			entryPointRequest.entrypointJavaFile(codeFile.getOriginalFilename(), inputFile, timeLimit, storageLimit);			
		}
		else if(language == "python") {
			folder = "program_python";
			file = "main.py";
			entryPointRequest.entrypointPythonFile(codeFile.getOriginalFilename(), inputFile, timeLimit, storageLimit);
		}
		
		
		LocalDateTime ldt = LocalDateTime.now();
		
		return ResponseEntity.status(HttpStatus.OK).body(""));
	}
}
