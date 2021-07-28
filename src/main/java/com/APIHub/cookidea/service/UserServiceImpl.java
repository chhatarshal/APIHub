package com.APIHub.cookidea.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.APIHub.cookidea.entity.User;
import com.APIHub.cookidea.model.UserModel;
import com.APIHub.cookidea.repository.UserRepository;
 
 
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	 
	
	 
	 private ModelMapper modelMapper = new ModelMapper();	

	@Override
	public UserModel saveUser(UserModel userModel) {
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
		UserModel userModel = modelMapper.map(user, UserModel.class);	    
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
}
