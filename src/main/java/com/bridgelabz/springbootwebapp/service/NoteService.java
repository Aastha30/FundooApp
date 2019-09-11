package com.bridgelabz.springbootwebapp.service;

import java.util.List;

import com.bridgelabz.springbootwebapp.dto.NoteDTO;
import com.bridgelabz.springbootwebapp.model.Note;

public interface NoteService {

	public Note createNote(NoteDTO noteDTO, String token) throws Exception;
	
	public Note updateNote(NoteDTO noteDTO,long noteID,String token) throws Exception;
	
	public void deleteNote(Long noteID,String token);
	
	public List<Note> fetchNote(String token);
	
	
	

	
}
