package com.example.booksharingapp.web;

import com.example.booksharingapp.model.Book;
import com.example.booksharingapp.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = UserNotAuthorizedBookController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class UserNotAuthorizedBookController {

    static final String REST_URL = "/api/books-available";

    @Autowired
    private final BookRepository bookRepository;

    @GetMapping
    public List<Book> getAvailable() {
        log.info("getAvailableBooks");
        return bookRepository.getAvailable();
    }

    @GetMapping("/{name}")
    public List<Book> getByName(@PathVariable String name) {
        log.info("getBook {}", name);
        return bookRepository.findByNameLikeIgnoreCase(name);
    }
}
