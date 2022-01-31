package com.booknotes.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booknotes.base.entity.Note;

public interface NoteRepository  extends JpaRepository<Note, Long> {
	
	 public List<Note> findAllByOrderByIdDesc();
	 
	  public List<Note> findByTrendingTrue();
	  
	  public List<Note> findBydeletedFalseOrderByViewCountDesc();
	  public List<Note> findByauthorNameIs(String authorName);
	 // to do 
	 // create a method to fetch notes for a particular user
	// public List<Note> findAllByUser();

} 
