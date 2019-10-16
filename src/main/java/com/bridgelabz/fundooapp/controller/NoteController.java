 package com.bridgelabz.fundooapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.dto.NoteDTO;
import com.bridgelabz.fundooapp.model.Note;
import com.bridgelabz.fundooapp.response.Response;
import com.bridgelabz.fundooapp.service.NoteService;
import com.bridgelabz.fundooapp.util.TokenUtil;

@RestController
@RequestMapping("/fundoo/note")
@CrossOrigin(origins="http://localhost:4200",exposedHeaders= {"jwt_token"})

public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	
	
	@PostMapping("/create")
	public ResponseEntity<Response> createNote(@RequestBody NoteDTO noteDTO,@RequestHeader String token) throws Exception
	{
		Response response= new Response();
		Note note=noteService.createNote(noteDTO, token);
		response.setStatusCode(200);
		response.setStatusMessage("Note created successfully");
		response.setBody(note);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Response> updateNote(@RequestBody Note note, @RequestHeader String token) throws Exception
	{
		Response response= new Response();
		noteService.updateNote(note,token);
		response.setStatusCode(200);
		response.setStatusMessage("Note Updated Successfully");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{noteID}")
	public ResponseEntity<Response> deleteNote(@PathVariable Long noteID, @RequestHeader String token)
	{
		Response response= new Response();
		noteService.deleteNote(noteID, token);
		response.setStatusCode(200);
		response.setStatusMessage("Note Deleted Successfully");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<Response> fetchNote(@RequestHeader String token)
	{
		Response response=new Response();
		List<Note> notes=noteService.fetchNote(token);
		response.setStatusCode(200);
		response.setStatusMessage("Fetched Notes of User ID: " +TokenUtil.verifyToken(token));
		response.setBody(notes);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/fetchArchive")
	public ResponseEntity<Response> fetchArchivedNote(@RequestHeader String token)
	{
		Response response=new Response();
		List<Note> archivedNotes=noteService.fetchArchivedNote(token);
		response.setStatusCode(200);
		response.setStatusMessage("Fetched Archived Notes of User ID: " +TokenUtil.verifyToken(token));
		response.setBody(archivedNotes);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/fetchTrash")
	public ResponseEntity<Response> fetchTrashedNote(@RequestHeader String token)
	{
		Response response=new Response();
		List<Note> trashedNotes=noteService.fetchTrashedNote(token);
		response.setStatusCode(200);
		response.setStatusMessage("Fetched Trashed Notes of User ID: " +TokenUtil.verifyToken(token));
		response.setBody(trashedNotes);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
