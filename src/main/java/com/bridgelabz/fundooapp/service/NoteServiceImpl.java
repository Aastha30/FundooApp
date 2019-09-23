package com.bridgelabz.fundooapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooapp.dto.NoteDTO;
import com.bridgelabz.fundooapp.exception.UserException;
import com.bridgelabz.fundooapp.model.Note;
import com.bridgelabz.fundooapp.repository.NoteRepository;
import com.bridgelabz.fundooapp.util.TokenUtil;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Note createNote(NoteDTO noteDTO, String token) throws Exception {

		Note note = modelMapper.map(noteDTO, Note.class);
		Long userID = TokenUtil.verifyToken(token);
		LocalDateTime currentTime = LocalDateTime.now();
		note.setUserID(userID);
		note.setCreatedTime(currentTime);
		note.setUpdatedTime(currentTime);
		note = noteRepository.save(note);
		return note;

	}

	@Override
	public Note updateNote(NoteDTO noteDTO, long noteID,String token){
		Long userID=TokenUtil.verifyToken(token);
		Optional<Note> noteToBeUpdated =noteRepository.findByUserIDAndNoteID(userID,noteID);
		Note updatedNote =noteToBeUpdated.map(existingNote -> {existingNote.setTitle(noteDTO.getTitle()!=null ? noteDTO.getTitle() : noteToBeUpdated.get().getTitle());
		existingNote.setDescription(noteDTO.getDescription()!=null ? noteDTO.getDescription(): noteToBeUpdated.get().getDescription());
		return existingNote;
		}).orElseThrow(() -> new UserException(404,"Note Not Found"));
		updatedNote.setUpdatedTime(LocalDateTime.now());
		return noteRepository.save(updatedNote);

	}

	@Override
	public void deleteNote(Long noteID, String token) {
		
		TokenUtil.verifyToken(token);
		try
		{
		noteRepository.deleteById(noteID);
		}
		catch(Exception e) {
			throw new UserException(404, "Note ID: " + noteID + " Not Found");
		}
		
	}

	@Override
	public List<Note> fetchNote(String token) {
		
		try
		{
		Long userID=TokenUtil.verifyToken(token);
		return noteRepository.findByUserID(userID);
		}
		catch(Exception e)
		{
			throw new UserException(404, "Token Not Found");
		}
	}

	

	

}
