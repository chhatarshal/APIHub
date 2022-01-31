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
	public NoteModel deleteNote(NoteModel noteModel, boolean softDelete);
	public NoteModel getNote(long id);
	public boolean publishNote(long noteId);
	public boolean unpublishNote(long noteId);
	public boolean notePrivacy(boolean privateState, long noteId);
	public boolean vote(boolean up, long noteId);
	public List<NoteModel> getAllMyNotes(long userId);
	public void updateNote(NoteModel noteModel);
	public List<NoteModel> getAllNotesIncludingDeleted();
	public List<NoteModel> searchNotesByTags(String tagContent);
	public List<NoteModel> getAllPublishedNotes(long userId);
	public List<NoteModel> getTrendingNotes();
	public List<NoteModel> getAllPublishedNotesByEmail(String email);
	
}
