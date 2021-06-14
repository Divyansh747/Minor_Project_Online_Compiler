package com.divyansh.online.compiler.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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

import com.divyansh.online.compiler.Entity.Response;
import com.divyansh.online.compiler.Entity.Result;

@Service
public class CompileRequest {
	
	@Autowired
	EntryPointRequest entryPointRequest;
	
	public ResponseEntity<Object> compile(MultipartFile codeFile, 
			MultipartFile inputFile, String language) throws IOException, InterruptedException{
		String folder = "";
		String file = "";
		
		if(language == "c") {
			folder = "program_c";
			file = "main.c";
			entryPointRequest.entrypointCFile(codeFile.getOriginalFilename(), inputFile);
		}
		else if(language == "cpp") {
			folder = "program_cpp";
			file = "main.cpp";
			entryPointRequest.entrypointCppFile(codeFile.getOriginalFilename(), inputFile);
		}
		else if(language == "java") {
			folder = "program_java";
			file = "main.java";
			entryPointRequest.entrypointJavaFile(codeFile.getOriginalFilename(), inputFile);			
		}
		else if(language == "python") {
			folder = "program_python";
			file = "main.py";
			entryPointRequest.entrypointPythonFile(codeFile.getOriginalFilename(), inputFile);
		}
		
		UploadFiles(codeFile, folder+"/"+file);
		
		if(inputFile != null) {
			UploadFiles(inputFile, folder+"/"+inputFile.getOriginalFilename());
		}
		
		LocalDateTime ldt = LocalDateTime.now();
		String image = "container"+ new Date().getTime();
		
		Result result = execProgram(folder, image);
		String statuscode = result.getStatus();
		
		removeFiles(folder, file);
		
		if(inputFile != null) {
			removeFiles(folder, inputFile.getOriginalFilename());
		}
		
		int status;
		String[] docker = new String[] {"docker", "rmi", image};
		ProcessBuilder processbuild = new ProcessBuilder(docker);
		Process process = processbuild.start();
		status = process.waitFor();
		
		return ResponseEntity.status(HttpStatus.OK).body(new Response(result.getOutput(), statuscode, ldt));
	}

	private Result execProgram(String folder, String image) throws IOException, InterruptedException {
		
		int status;
		String[] docker = new String[] {"docker", "image", "build", folder, "-t", image};
		ProcessBuilder processbuild = new ProcessBuilder(docker);
		Process process = processbuild.start();
		status = process.waitFor();
		
		String[] dockerRun = new String[] { "docker", "run", "--rm", image };
		ProcessBuilder processrun = new ProcessBuilder(dockerRun);
		Process processr = processrun.start();
		status = processr.waitFor();
		
		BufferedReader processReader = new BufferedReader(new InputStreamReader(processr.getInputStream()));
		BufferedReader errorReader = new BufferedReader(new InputStreamReader(processr.getErrorStream()));
		StringBuilder builder = new StringBuilder();
		
		String statusResponse;
		String line = null;

		while((line=processReader.readLine())!= null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}
		
		if(status == 0) {
			statusResponse = "Program Executed Successfully";
		}
		else {
			statusResponse = "Error during Program Compilation!";
			
			while((line=errorReader.readLine())!= null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			
		}
		
		return new Result(statusResponse, builder.toString());
	}

	private void UploadFiles(MultipartFile file, String filename) throws IOException {
		byte[] data = file.getBytes();
		Path loc = Paths.get(filename);
		Files.write(loc, data);
	}
	
	
	private boolean removeFiles(String folder, String file) {
		if(folder != null && file != null) {
			String filename = folder+"/"+file;
			new File(filename).delete();
			return true;
		}
		return false;
	}

}
