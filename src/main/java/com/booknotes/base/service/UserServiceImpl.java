package com.booknotes.base.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booknotes.base.entity.Book;
import com.booknotes.base.entity.Note;
import com.booknotes.base.entity.Settings;
import com.booknotes.base.entity.User;
import com.booknotes.base.model.BookModel;
import com.booknotes.base.model.SettingsModel;
import com.booknotes.base.model.UserModel;
import com.booknotes.base.repository.BookRepository;
import com.booknotes.base.repository.NoteRepository;
import com.booknotes.base.repository.SettingsRepository;
import com.booknotes.base.repository.UserRepository;
 
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;	
	
	@Autowired
	private BookRepository bookRepo;	
	
	@Autowired
	private NoteRepository noteRepo;	
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private SettingsRepository settingsRepo;
	 
	private ModelMapper modelMapper = new ModelMapper();	

	@Override
	public UserModel saveUser(UserModel userModel) {
		List<User> users = userRepo.findByEmailIs(userModel.getEmail());
		 
		if (users != null && users.size() > 0) {
			userModel.setId(users.get(0).getId());
			return userModel;
		}
		User user = userRepo.save(convertToUser(userModel));
		userModel.setId(user.getId());
		return userModel;
	}

	@Override
	public UserModel getUser(UserModel userModel) {
		return convertToUserModel(userRepo.getOne(userModel.getId()));
	}

	@Override
	public List<UserModel> getAllUsers() {
		return userRepo.findAll().stream().map(this::convertToUserModel).collect(Collectors.toList());
	}
	
	private UserModel convertToUserModel(User user) {
		List<BookModel> bookModel = new ArrayList<>();
		if (user.getMyBooks() != null) {
			user.getMyBooks().stream().forEach(book -> bookModel.add(new BookModel(book.getId(), book.getBookName())));
			user.setMyBooks(null);
		}
		UserModel userModel = modelMapper.map(user, UserModel.class);	
		userModel.setMyBooks(bookModel);
	    return userModel;
	}  
	
	private User convertToUser(UserModel userModel) {
		User user = modelMapper.map(userModel, User.class);	    
	    return user;
	}
	
	@Override
	public List<UserModel> getUserByName(String username) {
		return userRepo.findByUserName(username).stream().map(this::convertToUserModel).collect(Collectors.toList());
	}

	@Override
	public List<UserModel> addUserInChain(UserModel userModel) {
		return null;
	}

	@Override
	public List<UserModel> getAllChainedUser(Long userId) {
		return null;
	}

	@Override
	public UserModel addBookToUser(UserModel userModel, BookModel bookModel) {
		User user = userRepo.getOne(userModel.getId());
		boolean alreadyExist = user.getMyBooks().stream().filter(book -> book.getId() == bookModel.getId() || book.getBookName() == bookModel.getBookName()).findAny().isPresent();
		if (alreadyExist) {
			System.out.println("Book already present in user list");
			return userModel;
		}
		Book book = bookModel.getId() != 0L ? bookRepository.findById(bookModel.getId()).get() : bookRepository.findByBookName(bookModel.getBookName());
		user.getMyBooks().add(book);
		book.getUsers().add(user);
		userRepo.save(user);
		bookRepo.save(book);
		return userModel;
	}

	@Override
	public UserModel getUserByNamePassword(UserModel userModel) {
		User user = userRepo.findByUserName(userModel.getUserName()).get(0);
		return new UserModel(user.getId());
	}

	@Override
	public UserModel getUserById(long userId) {
		return convertToUserModel(userRepo.findById(userId).get());
	}
	
	@Override
	public boolean markSticky(long userId, long noteId, boolean value) {
		User user = userRepo.findById(userId).get();
		String stickyNotesId = user.getMyStickyNotes();
		if (stickyNotesId == null || stickyNotesId.length() < 1) {
			if (!value) {
				return false;
			}
			stickyNotesId = String.valueOf(noteId);
			user.setMyStickyNotes(stickyNotesId);
		} else {
			if (value) {
				String noteIds[] = stickyNotesId.split(",");
				for (String noteIdVar : noteIds) {
					if (noteIdVar.equals(String.valueOf(noteId))) {
						// because this note id already present
						return false;
					}
				}
				stickyNotesId = stickyNotesId + "," + noteId;
			} else {
				String notes[] = stickyNotesId.split(",");
				notes = Arrays.stream(notes).filter(note -> !note.equals(String.valueOf(noteId))).toArray(String[]::new);
				stickyNotesId = String.join(",", notes);
			}
			user.setMyStickyNotes(stickyNotesId);
		}
		userRepo.save(user);
		return true;
	}

	@Override
	public void saveSettings(SettingsModel settingsModel) {
		User user = userRepo.findById(settingsModel.getUserId()).orElse(null);
		if (user != null) {
			Settings settings = convertToSettings(settingsModel);
			user.setSettings(settings);
			settings.setUserSettings(user);
			settingsRepo.save(settings);
		}
		userRepo.save(user);
	}

	private Settings convertToSettings(SettingsModel settingsModel) {		 
		Settings settings = modelMapper.map(settingsModel, Settings.class);	
		return settings;
	}
}
