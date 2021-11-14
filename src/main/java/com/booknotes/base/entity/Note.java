package com.booknotes.base.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Note {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String content;
	private String details;
	private String tags;
	private String username;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="book_id", nullable=true)
	private Book book;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", nullable=true)
	private User user;
	
}
