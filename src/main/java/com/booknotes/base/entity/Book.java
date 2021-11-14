package com.booknotes.base.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private Long id;
	private Integer serialNo;
	private String bookName;
	private String details;
	private String author;
	private String pages;
	private String quality;
	private String price;
	private Date publishDate;
	private String edition;
	private String url;
	private String tags;
	@ManyToMany(mappedBy = "myBooks")
	private List<User> users = new ArrayList<>();
	
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval=true,
	        mappedBy="book"
	    )
	private List<Note> notes = new ArrayList<>();

}
