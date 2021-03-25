package com.divyansh.online.compiler.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.divyansh.online.compiler.Service.CompileRequest;

@RestController
public class ProgramController {
	
	@Autowired
	private CompileRequest compileRequest;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/language/c")
	public ResponseEntity<?> programC(@RequestPart(value = "codeFile", required = true) MultipartFile codeFile,
			@RequestParam(value = "inputFile", required = false) MultipartFile inputFile) throws IOException, InterruptedException{
		
		return compileRequest.compile(codeFile, inputFile, "c");
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/language/cpp")
	public ResponseEntity<?> programCpp(@RequestPart(value = "codeFile", required = true) MultipartFile codeFile,
			@RequestParam(value = "inputFile", required = false) MultipartFile inputFile) throws IOException, InterruptedException{
		
		return compileRequest.compile(codeFile, inputFile, "cpp");
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/language/java")
	public ResponseEntity<?> programJava(@RequestPart(value = "codeFile", required = true) MultipartFile codeFile,
			@RequestParam(value = "inputFile", required = false) MultipartFile inputFile) throws IOException, InterruptedException{
		
		return compileRequest.compile(codeFile, inputFile, "java");
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/language/python")
	public ResponseEntity<?> programPython(@RequestPart(value = "codeFile", required = true) MultipartFile codeFile,
			@RequestParam(value = "inputFile", required = false) MultipartFile inputFile) throws IOException, InterruptedException{
		
		return compileRequest.compile(codeFile, inputFile, "python");
	}
}
