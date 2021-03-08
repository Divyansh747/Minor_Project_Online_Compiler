package com.divyansh.online.compiler.Entity;

import javax.persistence.Entity;

@Entity
public class Result {
	
	private String status;
	private String output;
	private String requiredoutput;
	
	public Result(String status, String output, String requiredoutput) {
		super();
		this.status = status;
		this.output = output;
		this.requiredoutput = requiredoutput;
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

	public String getRequiredoutput() {
		return requiredoutput;
	}

	public void setRequiredoutput(String requiredoutput) {
		this.requiredoutput = requiredoutput;
	}
	
}
