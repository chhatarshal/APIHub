package com.booknotes.base.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoteModel {
	 
	public NoteModel(long noteId) {
		this.id = noteId;
	}
	public NoteModel() {
		
	}
	private long id;
	private String name;
	private String content;
	private String details;
	private String tags;
	private String username;
	private String image;
	private boolean publish;
	private boolean privateNote;
	private long viewCount;
	private boolean important;
	private boolean deleted;
	private long upvote;
	private long authorId;
}
