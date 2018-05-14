package com.bookdemonew.service;

import com.bookdemonew.entity.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBook();
    Book getBookById(long bookId);
    List<Book> getBookByTitle(String title);
    boolean addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int bookId);
}
