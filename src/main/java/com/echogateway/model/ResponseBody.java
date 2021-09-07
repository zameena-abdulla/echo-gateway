package com.echogateway.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBody {
	private String host;
	private String http;
	private String request;
	private String environment;	
	
	public ResponseBody() {
		
	}
}
