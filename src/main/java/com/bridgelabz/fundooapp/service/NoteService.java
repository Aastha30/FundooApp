package com.bridgelabz.fundooapp.service;

import java.time.LocalDateTime;
import java.util.List;


import com.bridgelabz.fundooapp.dto.NoteDTO;
import com.bridgelabz.fundooapp.model.Note;

public interface NoteService {

	public Note createNote(NoteDTO noteDTO, String token) throws Exception;
	
	public Note updateNote(Note note,String token) throws Exception;
	
	public void deleteNote(Long noteID,String token);
		
	public List<Note> fetchNote(String token);
	
	public List<Note> fetchArchivedNote(String token);
	
	public List<Note> fetchTrashedNote(String token);
	
	public Note addReminder(Long noteID, LocalDateTime reminder, String token);
	
	public Note removeReminder(Long noteID, String token);
	
	public List<Note> fetchReminderNote(String token);

}
