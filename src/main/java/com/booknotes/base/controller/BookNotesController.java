package com.booknotes.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booknotes.base.model.BookModel;
import com.booknotes.base.model.NoteModel;
import com.booknotes.base.model.UserModel;
import com.booknotes.base.service.BookService;
import com.booknotes.base.service.NoteService;
import com.booknotes.base.service.UserService;

 

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/booknotes/op")
public class BookNotesController {
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private NoteService noteService;
	
	@PostMapping("/saveuser")
	public UserModel createUser(@RequestBody UserModel userModel) {		
		return userService.saveUser(userModel);		
	}	
	
	// add book for user
	@PostMapping("/saveBook")
	public BookModel addBook(@RequestBody BookModel bookModel) {
		bookService.saveBook(bookModel);		
		return bookModel;		
	}
	
	// add book for user
	@GetMapping("/getAllBooks")
	public List<BookModel> getAllBooks() {		 		
		return bookService.getAllBooks();		
	}
	
	@GetMapping("/addBookToUserWithId")
	public UserModel addBookToUser(@RequestParam Long bookId, @RequestParam Long userId) {		 		
		return userService.addBookToUser(new UserModel(userId), new BookModel(bookId));		
	}
	
	@GetMapping("/addBookToUserWithName")
	public UserModel addBookToUser(@RequestParam Long userId, @RequestParam String bookName) {		 		
		return userService.addBookToUser(new UserModel(userId), new BookModel(bookName));		
	}
	
	@PostMapping("/deleteBook")
	public BookModel deleteBook(@RequestBody BookModel bookModel) {		 		
		return bookService.deleteBook(bookModel);		
	}
	
	@PostMapping("/getUser")
	public UserModel getUser(@RequestParam Long userId) {		 		
		return userService.getUser(new UserModel(userId));		
	}
	
	@PostMapping("/addNote")
	public String addNote(@RequestBody NoteModel noteModel, @RequestParam long bookId, @RequestParam long userId) {		 		
		bookService.addNote(noteModel, userId, bookId);	
		return "Note added";
	}
	
	@GetMapping("/getAllNotes")
	public List<NoteModel> getAllNotes() {
		return noteService.getAllNotes();
	}
	
	@GetMapping("/test")
	public String test() {		
		return "test";		
	}

}