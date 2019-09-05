package com.bridgelabz.springbootwebapp.dto;

import javax.persistence.Column;  
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class RegisterDTO {
	
	@NotNull(message="Please fill out this field")
	private String firstName;
	
	@NotNull(message="Please fill out this field")
	private String lastName;
	
	@NotNull(message="Please fill out this field")
	@Email(regexp="[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$",message="Please enter valid email address")
	private String emailID;
	
	@NotNull(message="Please fill out this field")
	@Pattern(regexp="[A-Za-z0-9]",message="Password must contain capital letter,small letter with atleast one digit ")
	private String password;
	
	@NotNull(message="Please fill out this field")
	@Column(length=10)
	@Pattern(regexp ="[7-9]{1}[0-9]{9}",message="Phone number must start from 7,8 or 9 with 10 digits")
	private long mobNum;
	
	@NotNull(message="Please fill out this field")
	private String gender;

}
