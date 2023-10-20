package com.example.booksharingapp.repository;

import com.example.booksharingapp.model.Book;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends BaseRepository<Book> {

    @Query("SELECT b FROM Book b WHERE b.availability=true")
    List<Book> getAvailable();

    //TODO make parameter case insensitive, too
    @Query("SELECT b FROM Book b WHERE LOWER(b.name) LIKE %:name%")
    List<Book> findByNameLikeIgnoreCase(String name);
}
