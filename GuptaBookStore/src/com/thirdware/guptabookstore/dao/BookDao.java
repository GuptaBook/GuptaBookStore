package com.thirdware.guptabookstore.dao;

import java.util.List;

import com.thirdware.guptabookstore.models.Author;
import com.thirdware.guptabookstore.models.Book;
import com.thirdware.guptabookstore.models.Subject;

public interface BookDao {
	Book insetBook(Book book);
	List<Book> fetchAllBook();
	Book fetchBookBId(int bid);
	List<Subject> fetchBookBySub();
	List<Author> fetchBookByAuth();
	Book updateBook(Book book);
}
