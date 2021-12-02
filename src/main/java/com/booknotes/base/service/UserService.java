package com.booknotes.base.service;

import java.util.List;

import com.booknotes.base.model.BookModel;
import com.booknotes.base.model.SettingsModel;
import com.booknotes.base.model.UserModel;

public interface UserService {

	public UserModel saveUser(UserModel userModel);
	public UserModel getUser(UserModel userModel);
	public UserModel getUserByNamePassword(UserModel userModel);
	public List<UserModel> getAllUsers();
	public List<UserModel> getUserByName(String username);
	public List<UserModel> addUserInChain(UserModel userModel);
	public List<UserModel> getAllChainedUser(Long userId);
	public UserModel addBookToUser(UserModel userModel, BookModel bookModel);
	public UserModel getUserById(long userId);
	public boolean markSticky(long userId, long noteId, boolean value);
	public void saveSettings(SettingsModel settingsModel);
}
