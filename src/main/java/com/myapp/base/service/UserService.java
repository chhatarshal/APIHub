package com.myapp.base.service;

import java.util.List;

import com.myapp.base.entity.Hero;
import com.myapp.base.model.KaroozModel;
import com.myapp.base.model.UserModel;

public interface UserService {

	public UserModel saveUser(UserModel userModel);
	public UserModel getUser(UserModel userModel);
	public List<UserModel> getAllUsers();
	public List<Hero> getAllHeros();
	public List<UserModel> getUserByName(String username);
	public List<KaroozModel> getAllKarooz(String userName);
	public List<UserModel> addUserInChain(UserModel userModel);
	public List<UserModel> getAllChainedUser(Long userId);
	public KaroozModel buyKarooz(Long karooId, Long buyerId, Long parentId);
	public List<KaroozModel> getAllKaroozOfUser(Long userId) ;
}
