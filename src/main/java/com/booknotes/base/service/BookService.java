package com.booknotes.base.service;

import java.util.List;

import com.booknotes.base.model.BookModel;
import com.booknotes.base.model.NoteModel;
import com.booknotes.base.model.UserModel;

public interface BookService {
	
	public BookModel saveBook(BookModel bookModel);
	public List<BookModel> getAllBooks();
	public List<BookModel> getAllBooksForUser(UserModel userModel);
	public BookModel deleteBook(BookModel bookModel);
	public void addNote(NoteModel noteModel, long userId, long bookId);
	public void addNote(NoteModel noteModel);	
}
