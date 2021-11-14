package com.booknotes.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booknotes.base.entity.Book;

public interface BookRepository  extends JpaRepository<Book, Long> {
	public Book findByBookName(String bookName);

}
