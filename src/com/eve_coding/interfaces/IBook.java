package com.eve_coding.interfaces;

import java.util.List;

import com.eve_coding.model.Book;

public interface IBook {

	boolean addBook(Book book);
	List<Book> getAllBooks();
	Book getBookDetails(String ISBN);
	boolean updateBook(Book book);
	boolean deleteBook(int id);
}
