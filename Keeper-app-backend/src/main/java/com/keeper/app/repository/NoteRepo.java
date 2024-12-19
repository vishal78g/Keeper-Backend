package com.keeper.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keeper.app.entity.Note;
import com.keeper.app.entity.User;

public interface NoteRepo extends JpaRepository<Note, Integer>{
	
	public List<Note> findByUser(User user);

}
