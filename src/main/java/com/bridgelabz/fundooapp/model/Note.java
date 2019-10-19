package com.bridgelabz.fundooapp.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long noteID;
	private Long userID;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	private boolean isArchive;
	private boolean isTrash;
	private boolean isPinned;
	private String color;
	@ManyToMany
	private List<Label> labels;
	private LocalDateTime reminder;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;

}
