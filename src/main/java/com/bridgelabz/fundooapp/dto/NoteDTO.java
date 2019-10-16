package com.bridgelabz.fundooapp.dto;

import lombok.Data;

@Data
public class NoteDTO {
	
	private String title;
	private String description;
	private boolean isArchive;
	private boolean isPinned;
	private String color;

}
