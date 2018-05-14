package com.bookdemonew.controller;

import java.util.List;

import com.bookdemonew.entity.Book;
import com.bookdemonew.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("user")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping("book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Integer id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }
    @GetMapping("book/title={title}")
    public ResponseEntity<List<Book>> getBookByTitle(@PathVariable("title") String title) {
        List<Book> list = bookService.getBookByTitle(title);
        return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
    }
    @GetMapping("book")
    public ResponseEntity<List<Book>> getAllBook() {
        List<Book> list = bookService.getAllBook();
        return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
    }

    @PostMapping("book")
    public ResponseEntity<Void> addBook(@RequestBody Book book, UriComponentsBuilder builder) {
        boolean flag = bookService.addBook(book);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/book/{id}").buildAndExpand(book.getBookId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("book")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}