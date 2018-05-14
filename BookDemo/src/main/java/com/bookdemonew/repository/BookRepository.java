package com.bookdemonew.repository;

import com.bookdemonew.entity.Book;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    //List<Book>findByTitleOrderByBookIdDesc(String title);
       @Query("SELECT b FROM Book b where b.title like %?1 order by b.author")
        List<Book>findbyTitle(String title);
 //   List<Article> findDistinctByCategory(String category);
    @Query("SELECT b from Book b where b.title like %?1 and b.author like %?2")
    List<Book> findBookByTitleandAuthor(String title, String author);
    @Query("select  b from  Book b")
    List<Book> findall();
    @Query("select b.title,b.author from Book b where b.bookId =:bookId")
    Optional<Book> findbyId (@Param("bookId") Long bookId);

}
