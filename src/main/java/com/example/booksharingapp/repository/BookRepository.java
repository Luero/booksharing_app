package com.example.booksharingapp.repository;

import com.example.booksharingapp.model.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends BaseRepository<Book> {

    @Query("SELECT b FROM Book b WHERE b.available=true")
    @Cacheable("books-available")
    List<Book> getAvailable();

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.owner u WHERE b.available=true")
    @Cacheable("books-available-with-contacts")
    List<Book> getAvailableWithUserDetail();

    //TODO make parameter case-insensitive, too
    @Query("SELECT b FROM Book b WHERE LOWER(b.name) LIKE %:name%")
    List<Book> findByNameLikeIgnoreCase(String name);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.owner u WHERE u.id=:id")
    List<Book> getByUserId(int id);
}
