package com.bridgelabz.fundooapp.model;

import java.time.LocalDateTime;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long userID;
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
	
	@OneToMany(mappedBy="userID")
	private List<Note> notes;
	
	
}
