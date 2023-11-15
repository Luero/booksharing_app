package com.example.booksharingapp.web;

import com.example.booksharingapp.AuthUser;
import com.example.booksharingapp.model.Book;
import com.example.booksharingapp.repository.BookRepository;
import com.example.booksharingapp.service.BookService;
import com.example.booksharingapp.to.BookTo;
import com.example.booksharingapp.util.ToUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
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
    @GetMapping("/available")
    public List<Book> getAvailable() {
        log.info("getAvailableBooks");
        return bookRepository.getAvailable();
    }

    // Access for all users, including unauthorised users
    @GetMapping("/find-by-{name}")
    public List<Book> getByName(@PathVariable String name) {
        log.info("getBook {}", name);
        return bookRepository.findByNameLikeIgnoreCase(name);
    }

    @GetMapping("/for-auth/my-books")
    public List<BookTo> getMyBooks(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getBooksForUserWithId {}", authUser.id());
        List<Book> books = bookRepository.getByUserId(authUser.id());
        List<BookTo> bookTolist = new ArrayList<>();
        for (Book book : books) {
            bookTolist.add(ToUtil.makeToFromBook(book));
        }
        return bookTolist;
    }

    @GetMapping("/for-auth/available-with-contacts")
    public List<Book> getAvailableWithContacts() {
        log.info("getAvailableWithContacts");
        return bookRepository.getAvailableWithUserDetail();
    }

    @DeleteMapping("/for-auth/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Caching(
            evict = {
                    @CacheEvict(value = "books-available", allEntries = true),
                    @CacheEvict(value = "books-available-with-contacts", allEntries = true)
            }
    )
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        bookRepository.deleteExisted(id);
    }

    @PostMapping(value = "/for-auth/add-book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> createWithLocation(@AuthenticationPrincipal AuthUser authUser,
                                                   @Valid @RequestBody BookTo bookTo) {
        log.info("create {}", bookTo);
        checkNew(bookTo);
        Book created = service.save(authUser.id(), bookTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/for-auth/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @Valid @RequestBody BookTo bookTo) {
        log.info("update book with id={}", id);
        service.update(id, bookTo);
    }
}
