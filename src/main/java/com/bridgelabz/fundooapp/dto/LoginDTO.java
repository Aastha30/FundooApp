package com.bridgelabz.fundooapp.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginDTO {
	
	@NotNull
	private String emailID;
	@NotNull
	private String password;

}
