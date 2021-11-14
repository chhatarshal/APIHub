package com.booknotes.base.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booknotes.base.entity.Book;
import com.booknotes.base.entity.Note;
import com.booknotes.base.entity.User;
import com.booknotes.base.model.BookModel;
import com.booknotes.base.model.NoteModel;
import com.booknotes.base.model.UserModel;
import com.booknotes.base.repository.BookRepository;
import com.booknotes.base.repository.NoteRepository;
import com.booknotes.base.repository.UserRepository;

@Service
public class BookServiceimpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NoteRepository noteRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public BookModel saveBook(BookModel bookModel) {
		Book book = convertToBook(bookModel);
		bookRepository.save(book);
		return bookModel;
	}

	@Override
	public List<BookModel> getAllBooks() {
		return bookRepository.findAll().stream().map(this::convertToBookModel).collect(Collectors.toList());
	}

	@Override
	public List<BookModel> getAllBooksForUser(UserModel userModel) {
		User user = userRepository.getOne(userModel.getId());
		List<BookModel> userBooks = user.getMyBooks().stream().map(this::convertToBookModel).collect(Collectors.toList());
		return userBooks;
	}
	
	@Override
	public BookModel deleteBook(BookModel bookModel) {
		bookRepository.deleteById(bookModel.getId());
		return bookModel;
	}
	
	@Override
	public void addNote(NoteModel noteModel, long userId, long bookId) {
		Note note = modelMapper.map(noteModel, Note.class);		
		note = noteRepository.save(note);
		Book book = bookRepository.findById(bookId).get();
		book.getNotes().add(note);
	//	note = noteRepository.findById(id)
		User user = userRepository.findById(userId).get();
		user.getNotes().add(note);
		userRepository.save(user);		
		bookRepository.save(book);
	}
	

	private BookModel convertToBookModel(Book book) {
		BookModel bookModel = modelMapper.map(book, BookModel.class);
		return bookModel;
	}

	private Book convertToBook(BookModel bookModel) {
		Book book = modelMapper.map(bookModel, Book.class);
		return book;
	}

	@Override
	public void addNote(NoteModel noteModel) {
		Note note = modelMapper.map(noteModel, Note.class);		
		note = noteRepository.save(note);
	}

	

	

}
