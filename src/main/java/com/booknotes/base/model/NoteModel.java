package com.booknotes.base.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoteModel {
	 
	private long id;
	private String name;
	private String content;
	private String details;
	private String tags;
	private String username;
	
}
