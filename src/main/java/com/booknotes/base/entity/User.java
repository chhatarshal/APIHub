package com.booknotes.base.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
	@Id
	@GeneratedValue
	private Long Id;
	private String userName;
	private String password;
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(name = "User_Book", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "book_id") })
	private List<Book> myBooks = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	private List<Note> notes = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	private List<Role> roles = new ArrayList<>();
	private String topics;
	private String email;
	private String phoneNumber;
	private String linkedInURL;
	private String fullName;
	private String hobbies;
	private String aboutMe;
	private boolean individual;
	private String organisationName;
	private String myStickyNotes;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userSettings")
	private Settings settings;
	private String image;
	private String authtype;
	private String name;

}
