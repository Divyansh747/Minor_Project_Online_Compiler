package com.divyansh.online.compiler.Entity;

public class Result {
	
	private String status;
	private String output;
	
	public Result(String status, String output) {
		super();
		this.status = status;
		this.output = output;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
