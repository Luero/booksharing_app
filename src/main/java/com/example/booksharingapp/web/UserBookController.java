package com.example.booksharingapp.web;

import com.example.booksharingapp.model.Book;
import com.example.booksharingapp.repository.BookRepository;
import com.example.booksharingapp.service.BookService;
import com.example.booksharingapp.to.BookTo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.example.booksharingapp.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = UserBookController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class UserBookController {

    static final String REST_URL = "/api/user/books";

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final BookService service;

    // Access for all users, including unauthorised users
    //TODO add to exceptions in Security
    @GetMapping("/available")
    public List<Book> getAvailable() {
        log.info("getAvailableBooks");
        return bookRepository.getAvailable();
    }

    // Access for all users, including unauthorised users
    //TODO add to exceptions in Security
    @GetMapping("/find-by-{name}")
    public List<Book> getByName(@PathVariable String name) {
        log.info("getBook {}", name);
        return bookRepository.findByNameLikeIgnoreCase(name);
    }

    //TODO replace with AuthUser after adding Security
    @GetMapping("/my-books")
    public List<Book> getMyBooks(@RequestParam int id) {
        log.info("getBooksForUserWithId {}", id);
        return bookRepository.getByUserId(id);
    }

    @GetMapping("/available-with-contacts")
    public List<Book> getAvailableWithContacts() {
        log.info("getAvailableWithContacts");
        return bookRepository.getAvailableWithUserDetail();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        bookRepository.deleteExisted(id);
    }

    //TODO replace with AuthUser after adding Security
    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> createWithLocation(@PathVariable int userId, @Valid @RequestBody BookTo bookTo) {
        log.info("create {}", bookTo);
        checkNew(bookTo);
        Book created = service.save(userId, bookTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @Valid @RequestBody BookTo bookTo) {
        log.info("update book with id={}", id);
        service.update(id, bookTo);
    }
}
