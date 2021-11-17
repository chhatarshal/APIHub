package com.booknotes.base.service;

import java.util.List;

import com.booknotes.base.model.BookModel;
import com.booknotes.base.model.NoteModel;
import com.booknotes.base.model.UserModel;

public interface NoteService {

	public NoteModel saveNote(NoteModel noteModel);
	public List<NoteModel> getAllNotes();
	public List<NoteModel> getAllNotesForUser(UserModel userModel);
	public List<NoteModel> getAllNotesForBook(BookModel bookModel);
	public NoteModel deleteNote(NoteModel noteModel);
	public NoteModel getNote(long id);
	public List<NoteModel> getAllPublishedNotes();
	public boolean publishNote(long noteId);
	public boolean unpublishNote(long noteId);
	
}
