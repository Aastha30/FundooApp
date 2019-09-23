package com.bridgelabz.fundooapp.exception;

import lombok.Data;

@Data
public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int errorCode;

	public UserException(int errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

}
