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
	
	@PostMapping("/User")
	public UserModel addUser(@RequestParam Long userId) {		 		
		return userService.getUser(new UserModel(userId));		
	}
	
	@PostMapping("/addNoteToBookAndUser")
	public String addNoteToBookAndUser(@RequestBody NoteModel noteModel, @RequestParam long bookId, @RequestParam long userId) {		 		
		bookService.addNote(noteModel, userId, bookId);	
		return "Note added";
	}
	
	@PostMapping("/addNote")
	public String addNote(@RequestBody NoteModel noteModel) {		 		
		noteService.saveNote(noteModel);	
		return "Note added";
	}
	
	@PostMapping("/updateNote")
	public String updateNote(@RequestBody NoteModel noteModel) {		 		
		noteService.updateNote(noteModel);	
		return "Note updated";
	}
	
	@GetMapping("/deleteNote")
	public String deleteNote(@RequestParam long noteId, @RequestParam(defaultValue = "true") boolean softDelete) {		 		
		noteService.deleteNote(new NoteModel(noteId), softDelete);
		return "Note deleted with id " + noteId;
	}
	
	@GetMapping("/publishNote")
	public boolean publishNote(@RequestParam long noteId) {		 		
		return noteService.publishNote(noteId);
	}
	
	@GetMapping("/unpublishNote")
	public boolean unpublishNote(@RequestParam long noteId) {		 		
		return noteService.unpublishNote(noteId);
	}
	
	@GetMapping("/getAllNotes")
	public List<NoteModel> getAllNotes() {
		return noteService.getAllNotes();
	}
	
	@GetMapping("/searchNotesByTags")
	public List<NoteModel> searchNotesByTags(@RequestParam String tagContent) {
		return noteService.searchNotesByTags(tagContent);
	}
	
	@GetMapping("/getAllNotesIncludingDeleted")
	public List<NoteModel> getAllNotesIncludingDeleted() {
		return noteService.getAllNotesIncludingDeleted();
	}
	
	@GetMapping("/getAllMyNotes")
	public List<NoteModel> getAllMyNotes(@RequestParam long userId) {
		return noteService.getAllMyNotes(userId);
	}
	
	@GetMapping("/getAllPublishedNotes")
	public List<NoteModel> getAllPublishedNotes() {
		return noteService.getAllPublishedNotes();
	}	
	
	@GetMapping("/getAllNoteById")
	public NoteModel getAllNoteById(@RequestParam long noteId) {
		return noteService.getNote(noteId);
	}
	
	@GetMapping("/notePrivacy")
	public boolean notePrivacy(@RequestParam boolean privateState, @RequestParam long noteId) {
		return noteService.notePrivacy(privateState, noteId);
	}
	
	@GetMapping("/vote")
	public boolean vote(@RequestParam boolean up, @RequestParam long noteId) {
		return noteService.vote(up, noteId);
	}
	
	@GetMapping("/test")
	public String test() {		
		return "test";		
	}

}
