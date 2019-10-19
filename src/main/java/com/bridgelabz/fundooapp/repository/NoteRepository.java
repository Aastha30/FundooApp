package com.bridgelabz.fundooapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.fundooapp.model.Note;

public interface NoteRepository extends JpaRepository<Note,Long>{
	
	Optional<Note> findByUserIDAndNoteID(long userID,long noteID);

	@Query("from Note n where n.isArchive=:isArchive and n.isTrash=:isTrash and n.userID=:userID")
	List<Note> findByUserID(Long userID, boolean isArchive, boolean isTrash);
	
	@Query("from Note n where n.reminder is not null and n.userID=:userID")
	List<Note> findAllReminder(Long userID);
	}
