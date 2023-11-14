package com.example.booksharingapp.service;

import com.example.booksharingapp.model.Book;
import com.example.booksharingapp.repository.BookRepository;
import com.example.booksharingapp.repository.UserRepository;
import com.example.booksharingapp.to.BookTo;
import com.example.booksharingapp.util.ToUtil;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository repository;

    private UserRepository userRepository;

    //https://habr.com/ru/articles/465667/
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(value = "books-available", allEntries = true),
                    @CacheEvict(value = "books-available-with-contacts", allEntries = true)
            }
    )
    public Book save(int userId, BookTo bookTo) {
        Book book = ToUtil.createFromBookTo(bookTo);
        book.setOwner(userRepository.getExisted(userId));
        return repository.save(book);
    }

    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(value = "books-available", allEntries = true),
                    @CacheEvict(value = "books-available-with-contacts", allEntries = true)
            }
    )
    public Book update(int id, BookTo bookTo) {
        Book book = ToUtil.updateFromBookTo(repository.getExisted(id), bookTo);
        return repository.save(book);
    }
}
