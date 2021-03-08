package com.divyansh.online.compiler.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.divyansh.online.compiler.Entity.Result;

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
		
		UploadFiles(codeFile, folder+"/"+file);
		UploadFiles(outpFile, folder+"/"+outpFile.getOriginalFilename());
		
		if(inputFile != null) {
			UploadFiles(inputFile, folder+"/"+inputFile.getOriginalFilename());
		}
		
		LocalDateTime ldt = LocalDateTime.now();
		String image = "container"+ new Date().getTime();
		
		Result result = execProgram(folder, image, outpFile);
		
		return ResponseEntity.status(HttpStatus.OK).body(""));
	}
	
	private Result execProgram(String folder, String image, MultipartFile outpFile) {
		
		return null;
	}

	private void UploadFiles(MultipartFile file, String filename) throws IOException {
		byte[] data = file.getBytes();
		Path loc = Paths.get(filename);
		Files.write(loc, data);
	}
}
