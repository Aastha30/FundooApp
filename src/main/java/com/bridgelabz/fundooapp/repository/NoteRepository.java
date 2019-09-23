package com.bridgelabz.fundooapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundooapp.model.Note;

public interface NoteRepository extends JpaRepository<Note,Long>{
	
	Optional<Note> findByUserIDAndNoteID(long userID,long noteID);

	List<Note> findByUserID(Long userID);

}
