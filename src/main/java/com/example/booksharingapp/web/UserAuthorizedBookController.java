package com.example.booksharingapp.web;

import com.example.booksharingapp.model.Book;
import com.example.booksharingapp.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = UserAuthorizedBookController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class UserAuthorizedBookController {

    static final String REST_URL = "/api/user";

    @Autowired
    private final BookRepository bookRepository;

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

    //TODO adding books to the list, updating them and deleling them
}
