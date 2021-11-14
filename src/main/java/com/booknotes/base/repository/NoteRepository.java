package com.booknotes.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booknotes.base.entity.Note;

public interface NoteRepository  extends JpaRepository<Note, Long> {

}
