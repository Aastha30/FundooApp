package com.bridgelabz.fundooapp.response;

import lombok.Data;

@Data
public class Response {
	
	private int statusCode;
	private String statusMessage;
	private Object body;
}
