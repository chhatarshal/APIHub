package com.booknotes.base.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
	@Lob 
	@Column(name="CONTENT", length=512)
	private String content;
	@Lob 
	@Column(name="NOTE_CONTENT", length=512)
	private String details;
	private String tags;
	private String username;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="book_id", nullable=true)
	private Book book;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", nullable=true)
	private User user;
	private String image;
	private boolean publish;
	private boolean privateNote;
	private long viewCount;
	private boolean important;
	private boolean deleted;
	private long upvote;
	private long authorId;
	private String authorName;
	private String title;
	private boolean sticky;
	
}
