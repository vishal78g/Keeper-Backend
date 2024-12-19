package com.keeper.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keeper.app.payload.NoteDto;
import com.keeper.app.payload.UserDto;
import com.keeper.app.serviceImpl.NoteServiceImpl;

@RestController
@RequestMapping("/api")
public class NoteController {

	@Autowired
	private NoteServiceImpl noteService;
	
	//create note
	@PostMapping("/user/notes")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> createNote(@RequestBody NoteDto noteDto){
		UserDto user=noteDto.getUser();
		int userId=user.getId();
		NoteDto crateNote = noteService.crateNote(userId, noteDto);
		return ResponseEntity.ok(crateNote);
	}
	
	//get all notes of user
	@GetMapping("/user/notes/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<NoteDto>> getAllNotes(@PathVariable Integer id){
		List<NoteDto> allNotes = this.noteService.getAllNotes(id);
		return ResponseEntity.ok(allNotes);
	}
	
	
	//delete note 
	@DeleteMapping("/user/notes/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Boolean> deleteNote(@PathVariable Integer id){
		if(this.noteService.deleteNote(id)) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.ok(false);			
		}
	}
	
	
}
