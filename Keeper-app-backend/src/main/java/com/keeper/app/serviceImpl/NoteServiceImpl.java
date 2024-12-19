package com.keeper.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keeper.app.entity.Note;
import com.keeper.app.entity.User;
import com.keeper.app.exceptions.ResourceNotFoundException;
import com.keeper.app.payload.NoteDto;
import com.keeper.app.repository.NoteRepo;
import com.keeper.app.repository.UserRepo;
import com.keeper.app.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private UserRepo userRepo;
	
	
	@Autowired
	private NoteRepo noteRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public NoteDto crateNote(Integer userId, NoteDto noteDto) {
		User user = userRepo.findById(userId).
				orElseThrow(() ->new ResourceNotFoundException("User","user id" , userId));
		Note note=mapper.map(noteDto, Note.class);
		note.setTitle(noteDto.getTitle());
		note.setContent(noteDto.getContent());
		note.setUser(user);
		Note newNote=noteRepo.save(note);
		return mapper.map(newNote, NoteDto.class);
	}

	@Override
	public NoteDto updateNote(Integer userId, Integer noteId, NoteDto noteDto) {
		User user = userRepo.findById(userId).
				orElseThrow(() ->new ResourceNotFoundException("User","user id" , userId));
		Note note = noteRepo.findById(noteId).
				orElseThrow(() ->new ResourceNotFoundException("Note","note id" , noteId));
		note.setTitle(noteDto.getTitle());
		note.setContent(noteDto.getContent());
		note.setUser(user);
		Note newNote=noteRepo.save(note);
		return mapper.map(newNote, NoteDto.class);
	}

	@Override
	public NoteDto getNote(Integer userId, Integer noteId) {
		userRepo.findById(userId).
				orElseThrow(() ->new ResourceNotFoundException("User","user id" , userId));
		Note note = noteRepo.findById(noteId).
				orElseThrow(() ->new ResourceNotFoundException("Note","note id" , noteId));
		return mapper.map(note, NoteDto.class);
	}

	@Override
	public List<NoteDto> getAllNotes(Integer userId) {
		User user = userRepo.findById(userId).
				orElseThrow(() ->new ResourceNotFoundException("User","user id" , userId));
		List<Note> notesList=noteRepo.findByUser(user);
		List<NoteDto> list=notesList.stream().map((note) -> 
				mapper.map(note, NoteDto.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public boolean deleteNote(Integer noteId) {
		Note note = noteRepo.findById(noteId).
				orElseThrow(() ->new ResourceNotFoundException("Note","note id" , noteId));
		noteRepo.delete(note);
		return true;
	}

	

}
