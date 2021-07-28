package com.APIHub.cookidea.service;

import java.util.List;

import com.APIHub.cookidea.model.UserModel;

 

public interface UserService {

	public UserModel saveUser(UserModel userModel);
	public UserModel getUser(UserModel userModel);
	public List<UserModel> getAllUsers();
	public List<UserModel> getUserByName(String username);
	public List<UserModel> addUserInChain(UserModel userModel);
	public List<UserModel> getAllChainedUser(Long userId); 
}
