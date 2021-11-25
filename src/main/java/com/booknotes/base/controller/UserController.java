package com.booknotes.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booknotes.base.model.UserModel;
import com.booknotes.base.service.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/booknotes/op")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getUserByUserid")
	public UserModel getUserByUserid(@RequestParam long userId) {		 		
		return userService.getUserById(userId);		
	}

}
