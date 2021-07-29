package com.myapp.base.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.base.entity.Hero;
import com.myapp.base.entity.Karooz;
import com.myapp.base.entity.User;
import com.myapp.base.model.KaroozModel;
import com.myapp.base.model.UserModel;
import com.myapp.base.repository.KaroozRepository;
import com.myapp.base.repository.UserRepository;
 
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private KaroozRepository karoozRepository;
	
	 @Autowired
	 private ModelMapper modelMapper;	

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
	
	private KaroozModel convertToKaroozModel(Karooz karooz) {
		KaroozModel KaroozModel = modelMapper.map(karooz, KaroozModel.class);	    
	    return KaroozModel;
	}
	
	private User convertToUser(UserModel userModel) {
		User user = modelMapper.map(userModel, User.class);	    
	    return user;
	}
	
	private Hero convertToHeros(User user) {
		Hero hero = modelMapper.map(user, Hero.class);	
		hero.setName(user.getUserName());
	    return hero;
	}

	@Override
	public List<Hero> getAllHeros() {
		return userRepo.findAll().stream().map(this::convertToHeros).collect(Collectors.toList());
	}

	@Override
	public List<UserModel> getUserByName(String username) {
		return userRepo.findByUserName(username).stream().map(this::convertToUserModel).collect(Collectors.toList());
	}

	@Override
	public List<KaroozModel> getAllKarooz(String userName) {
		List<User> users = userRepo.findByUserName(userName);
		User user = null;
		if (users != null && users.size() == 1) {
			user = users.get(0);
		}
		if (users != null && users.size() > 1) {
			user = users.get(0);
		}
		List<KaroozModel> karoozs = user.getKaroozs().stream().map(this::convertToKaroozModel).collect(Collectors.toList());
		return karoozs;
	}

	@Override
	public List<UserModel> addUserInChain(UserModel userModel) {
		User user = userRepo.findById(userModel.getParentUserId()).get();
		User newUser = convertToUser(userModel);
		user.getMyChain().add(newUser);
		userRepo.save(user);
		List<UserModel> userModels = user.getMyChain().stream().map(this::convertToUserModel).collect(Collectors.toList());
		return userModels;
	}

	@Override
	public List<UserModel> getAllChainedUser(Long userId) {
		User user = userRepo.findById(userId).get();
		List<UserModel> userModels = user.getMyChain().stream().map(this::convertToUserModel).collect(Collectors.toList());
		return userModels;
	}

	@Override
	public KaroozModel buyKarooz(Long karooId, Long buyerId, Long parentId) {
		User user = userRepo.findById(buyerId).get();
		User parentUser = userRepo.findById(parentId).get();
		Karooz karooz = karoozRepository.findById(karooId).get();
		parentUser.getMyChain().add(user);
		userRepo.save(parentUser);
		user.getKaroozs().add(karooz);
		userRepo.save(user);
		return convertToKaroozModel(karooz);
	}
	
	@Override
	public List<KaroozModel> getAllKaroozOfUser(Long userId) {
		User user = userRepo.findById(userId).get();
		return user.getKaroozs().stream().map(this::convertToKaroozModel).collect(Collectors.toList());
	}

}
