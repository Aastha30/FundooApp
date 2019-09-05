package com.bridgelabz.springbootwebapp.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	@Column(nullable=false)
	private String firstName;
	@Column(nullable=false)
	private String lastName;
	@Column(nullable=false,unique=true)
	private String emailID;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false,unique=true)
	private long mobNum;
	@Column(nullable=false)
	private String gender;
	@DateTimeFormat
	private LocalDateTime createdTime;
	@DateTimeFormat
	private LocalDateTime updatedTime;
	private boolean isVerified;
	
}
