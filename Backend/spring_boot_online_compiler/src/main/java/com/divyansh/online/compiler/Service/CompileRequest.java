package com.divyansh.online.compiler.Service;

import java.io.File;
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

import com.divyansh.online.compiler.Entity.Response;
import com.divyansh.online.compiler.Entity.Result;

@Service
public class CompileRequest {
	
	@Autowired
	EntryPointRequest entryPointRequest;
	
	public ResponseEntity<Object> compile(MultipartFile codeFile, MultipartFile outpFile, 
			MultipartFile inputFile, int timeLimit, int storageLimit, String language) throws IOException{
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
		
		String statuscode = result.getStatus();
		
		removeFiles(folder, file);
		removeFiles(folder, outpFile.getOriginalFilename());
		removeFiles(folder, inputFile.getOriginalFilename());
		
		return ResponseEntity.status(HttpStatus.OK).body(new Response(result.getOutput(), result.getRequiredoutput(), statuscode, ldt));
	}
	
	private boolean removeFiles(String folder, String file) {
		if(folder != null && file != null) {
			String filename = folder+"/"+file;
			new File(filename).delete();
			return true;
		}
		return false;
	}

	private Result execProgram(String folder, String image, MultipartFile outpFile) throws IOException, InterruptedException {
		
		int status;
		String[] docker = new String[] {"docker image build", folder, "-t", image};
		ProcessBuilder processbuild = new ProcessBuilder(docker);
		Process process = processbuild.start();
		status = process.waitFor();
		
		String[] dockerRun = new String[] { "docker run --rm", image };
		ProcessBuilder processrun = new ProcessBuilder(dockerRun);
		Process processr = processrun.start();
		status = processr.waitFor();
		
		return null;
	}

	private void UploadFiles(MultipartFile file, String filename) throws IOException {
		byte[] data = file.getBytes();
		Path loc = Paths.get(filename);
		Files.write(loc, data);
	}
}
