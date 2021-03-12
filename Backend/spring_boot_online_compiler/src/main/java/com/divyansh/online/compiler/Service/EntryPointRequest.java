package com.divyansh.online.compiler.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EntryPointRequest {

	public void entrypointCFile(String originalFilename, MultipartFile inputFile, int timeLimit, int storageLimit) {
		String cmd;
		String entryfile;
		
		if(inputFile == null) {
			cmd = "timeout --signal=SIGTERM " + timeLimit + " ./output" + "\n";
		}else {
			cmd = "timeout --signal=SIGTERM " + timeLimit + " ./output" + "< " + inputFile.getOriginalFilename() + "\n";
		}
		
		entryfile = "#!/bin/bash" + "\n" +
					"mv " + originalFilename + " main.c" + "\n" +
					"gcc main.c -o output" + "\n" + 
					"if [ $? -ne 0 ]" + "\n" +
					"  then" + "\n" +  
					"    exit 2" + "\n" +
					"fi" + "\n" +
					"ulimit -s " + storageLimit + "\n" +
					cmd + "\n" + "exit $?";
		
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File("program_c/compilecode.sh"));
			os.write(entryfile.getBytes(), 0, entryfile.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void entrypointCppFile(String originalFilename, MultipartFile inputFile, int timeLimit, int storageLimit) {
		String cmd;
		String entryfile;
		
		if(inputFile == null) {
			cmd = "timeout --signal=SIGTERM " + timeLimit + " ./output" + "\n";
		}else {
			cmd = "timeout --signal=SIGTERM " + timeLimit + " ./output" + "< " + inputFile.getOriginalFilename() + "\n";
		}
		
		entryfile = "#!/bin/bash" + "\n" + 
					"mv " + originalFilename + " main.cpp" + "\n" +
					"g++ main.cpp -o output" + "\n" +
					"if [ $? -ne 0 ]" + "\n" +
					"  then" + "\n" +  
					"    exit 2" + "\n" +
					"fi" + "\n" +
					"ulimit -s " + storageLimit + "\n" +
					cmd + "\n" + "exit $?";
		
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File("program_cpp/compilecode.sh"));
			os.write(entryfile.getBytes(), 0, entryfile.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void entrypointJavaFile(String originalFilename, MultipartFile inputFile, int timeLimit, int storageLimit) {
		String cmd;
		String entryfile;
		
		if(inputFile == null) {
			cmd = "timeout --signal=SIGTERM " + timeLimit + " java main" + "\n";
		}else {
			cmd = "timeout --signal=SIGTERM " + timeLimit + " java main " + "< " + inputFile.getOriginalFilename() + "\n";
		}
		
		entryfile = "#!/bin/bash" + "\n" + 
					"mv " + originalFilename + " main.java" + "\n" +
					"javac main.java " + "\n" +
					"if [ $? -ne 0 ]" + "\n" +
					"  then" + "\n" +  
					"    exit 2" + "\n" +
					"fi" + "\n" +
					"ulimit -s " + storageLimit + "\n" +
					cmd + "\n" + "exit $?";
		
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File("program_java/compilecode.sh"));
			os.write(entryfile.getBytes(), 0, entryfile.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void entrypointPythonFile(String originalFilename, MultipartFile inputFile, int timeLimit, int storageLimit) {
		String cmd;
		String entryfile;
		
		if(inputFile == null) {
			cmd = "timeout --signal=SIGTERM " + timeLimit + " python main.py " + "\n";
		}else {
			cmd = "timeout --signal=SIGTERM " + timeLimit + " python main.py " + "< " + inputFile.getOriginalFilename() + "\n";
		}
		
		entryfile = "#!/bin/bash" + "\n" + 
					"mv " + originalFilename + " main.py " + "\n" +
					"ulimit -s " + storageLimit + "\n" +
					cmd + "\n" + "exit $?";
		
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File("program_python/compilecode.sh"));
			os.write(entryfile.getBytes(), 0, entryfile.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	
}
