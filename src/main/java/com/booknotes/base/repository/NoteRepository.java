package com.booknotes.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booknotes.base.entity.Note;

public interface NoteRepository  extends JpaRepository<Note, Long> {
	
	 public List<Note> findAllByOrderByIdDesc();
	 // to do 
	 // create a method to fetch notes for a particular user
	// public List<Note> findAllByUser();

} 
