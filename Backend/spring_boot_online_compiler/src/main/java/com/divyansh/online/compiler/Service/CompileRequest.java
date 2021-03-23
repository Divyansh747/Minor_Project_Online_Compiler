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
	
	public ResponseEntity<Object> compile(MultipartFile codeFile, MultipartFile outpFile, 
			MultipartFile inputFile, int timeLimit, int storageLimit, String language) throws IOException, InterruptedException{
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
		
		if(inputFile != null) {
			removeFiles(folder, inputFile.getOriginalFilename());
		}
		
		int status;
		String[] docker = new String[] {"sudo","docker", "rmi", image};
		ProcessBuilder processbuild = new ProcessBuilder(docker);
		Process process = processbuild.start();
		status = process.waitFor();
		
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
		String[] docker = new String[] {"sudo","docker", "image", "build", folder, "-t", image};
		ProcessBuilder processbuild = new ProcessBuilder(docker);
		Process process = processbuild.start();
		status = process.waitFor();
		
		String[] dockerRun = new String[] { "docker", "run", "--rm", image };
		ProcessBuilder processrun = new ProcessBuilder(dockerRun);
		Process processr = processrun.start();
		status = processr.waitFor();
		
		BufferedReader outputFileReader = new BufferedReader(new InputStreamReader(outpFile.getInputStream()));
		StringBuilder outputBuilder = new StringBuilder();
		BufferedReader processReader = new BufferedReader(new InputStreamReader(processr.getInputStream()));
		StringBuilder builder = new StringBuilder();
		
		boolean result = verifyResult(outputFileReader, outputBuilder, processReader, builder); 
		String statusResponse;
		
		if(result == true && status == 0) {
			statusResponse = "Accepted";
		}
		else {
			statusResponse = "Not Accepted";
		}
		
		return new Result(statusResponse, builder.toString(), outputBuilder.toString());
	}

	private boolean verifyResult(BufferedReader outputFileReader, StringBuilder outputBuilder,
			BufferedReader processReader, StringBuilder builder) throws IOException {
		
		String line = null;
		String outputLine = null;
		boolean ans = true;
		
		while(((line=processReader.readLine()) != null ) && (outputLine=outputFileReader.readLine()) != null) {
			if(!line.equals(outputLine)) {
				ans = false;
			}
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
			
			outputBuilder.append(outputLine);
			outputBuilder.append(System.getProperty("line.separator"));
			
		}
		
		if(line != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}
		
		if(outputLine != null) {
			outputBuilder.append(outputLine);
			outputBuilder.append(System.getProperty("line.separator"));
		}
		
		while((line=processReader.readLine())!= null) {
			ans = false;
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}
		
		while((outputLine=outputFileReader.readLine())!= null) {
			ans = false;
			outputBuilder.append(outputLine);
			outputBuilder.append(System.getProperty("line.separator"));
		}
		
		return ans;
	}

	private void UploadFiles(MultipartFile file, String filename) throws IOException {
		byte[] data = file.getBytes();
		Path loc = Paths.get(filename);
		Files.write(loc, data);
	}
}
