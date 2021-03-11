package com.divyansh.online.compiler.Entity;

import java.time.LocalDateTime;

public class Response {
	private String output;
	private String requiredoutput;
	private String statuscode;
	private LocalDateTime ldt;
	
	public Response(String output, String requiredoutput, String statuscode, LocalDateTime ldt) {
		super();
		this.output = output;
		this.requiredoutput = requiredoutput;
		this.statuscode = statuscode;
		this.ldt = ldt;
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

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public LocalDateTime getLdt() {
		return ldt;
	}

	public void setLdt(LocalDateTime ldt) {
		this.ldt = ldt;
	} 
	
}
