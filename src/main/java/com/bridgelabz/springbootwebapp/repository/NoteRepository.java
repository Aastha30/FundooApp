package com.bridgelabz.springbootwebapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.springbootwebapp.model.Note;

public interface NoteRepository extends JpaRepository<Note,Long>{
	
	Optional<Note> findByUserIDAndNoteID(long userID,long noteID);

	List<Note> findByUserID(Long userID);

}
