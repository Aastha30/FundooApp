package com.bridgelabz.springbootwebapp.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ResetPasswordDTO {
	
	@NotNull
	private String newPassword;
	@NotNull
	private String confirmPassword;

}
