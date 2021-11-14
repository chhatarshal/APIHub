package com.booknotes.base.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookModel {
	

	public BookModel(long id) {
		this.id = id;
	}
	
	public BookModel(String bookName) {
		this.bookName = bookName;
	}
	
	public BookModel(long id, String name) {
		this.id = id;
		this.bookName = name;
	}
	
	public BookModel() {
		
	}
	
	private long id;
	private int serialNo;
	private String bookName;
	private String details;
	private String author;
	private String pages;
	private String quality;
	private String price;
	private Date publishDate;
	private String edition;
	private String tags;
	private String url;

}
