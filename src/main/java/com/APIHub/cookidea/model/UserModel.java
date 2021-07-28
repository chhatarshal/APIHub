package com.APIHub.cookidea.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserModel {
	
	private Long Id;
	private Long parentUserId;
	private String userName;
	private String password;
}
