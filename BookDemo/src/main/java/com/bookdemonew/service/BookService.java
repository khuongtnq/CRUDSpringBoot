package com.bookdemonew.service;

import com.bookdemonew.entity.Book;
import com.bookdemonew.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book getBookById(long bookId) {
        Book obj = bookRepository.findById(bookId).get();
        return obj;
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        List<Book> list = bookRepository.findbyTitle(title);
        return list;

    }
    @Override
    public List<Book> getAllBook(){
        List<Book> list = bookRepository.findall();
        return list;
        // List<Book> list = new ArrayList<>();
        // list = bookRepository.findall();
    }
    @Override
    public synchronized boolean addBook(Book book){
        List<Book> list = bookRepository.findBookByTitleandAuthor(book.getTitle(), book.getAuthor());
        if (list.size() > 0) {
            return false;
        } else {
            bookRepository.save(book);
            return true;
        }
    }
    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }
    @Override
    public void deleteBook(int bookId) {
        bookRepository.delete(getBookById(bookId));
    }

}
