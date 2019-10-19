package com.bridgelabz.fundooapp.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoteDTO {
	
	private String title;
	private String description;
	private boolean isArchive;
	private boolean isPinned;
	private String color;
	private LocalDateTime reminder;

}
