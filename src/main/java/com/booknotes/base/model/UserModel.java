package com.booknotes.base.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserModel {
	
	public UserModel(long id) {
		this.Id = id;
	}
	public UserModel() {
		
	}
	private long Id;
	private Long parentUserId;
	private String userName;
	private String password;
	private List<BookModel> myBooks = new ArrayList<>();
	
	private String topics;
	private String email;
	private String phoneNumber;
	private String linkedInURL;
	private String fullName;
	private String hobbies;
	private String aboutMe;
}
