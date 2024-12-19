package com.keeper.app.service;

import java.util.List;

import com.keeper.app.payload.NoteDto;

public interface NoteService {
	
	//create 
	public NoteDto crateNote(Integer userId, NoteDto noteDto);
	
	//update
	public NoteDto updateNote(Integer userId, Integer noteId, NoteDto noteDto);
	
	//get
	public NoteDto getNote(Integer userId, Integer noteId);
	
	//getall
	public List<NoteDto>  getAllNotes(Integer userId);
	
	//delete
	public boolean deleteNote(Integer noteId);
	

}
