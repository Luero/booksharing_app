package com.example.booksharingapp.web;

import com.example.booksharingapp.model.BorrowLog;
import com.example.booksharingapp.repository.BorrowLogRepository;
import com.example.booksharingapp.service.BorrowLogService;
import com.example.booksharingapp.to.BorrowLogDTOBorrowed;
import com.example.booksharingapp.to.BorrowLogDTOLent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserBorrowLogController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class UserBorrowLogController {

    static final String REST_URL = "/api/user/borrow-logs";

    @Autowired
    public final BorrowLogRepository repository;

    @Autowired
    private final BorrowLogService service;

    //TODO replace with AuthUser after adding Security
    @GetMapping("/borrowed")
    public List<BorrowLogDTOBorrowed> getLogsForBorrowedBooksNotReturned(@RequestParam int userId) {
        log.info("getInfoAboutBorrowedBooksNotReturnedForUserWithId {}", userId);
        return repository.getLogsForBorrowedBooksByUserIdNotReturned(userId);
    }

    //TODO replace with AuthUser after adding Security
    @GetMapping("/lent")
    public List<BorrowLogDTOLent> getLogsForLentBooksNotReturned(@RequestParam int userId) {
        log.info("getInfoAboutLentBooksNotReturnedForUserWithId {}", userId);
        return repository.getLogsForLentBooksByUserIdNotReturned(userId);
    }

    //TODO replace with AuthUser after adding Security
    @GetMapping("/borrowed/history")
    public List<BorrowLogDTOBorrowed> getLogsForBorrowedBooks(@RequestParam int userId) {
        log.info("getInfoAboutBorrowedBooksForUserWithId {}", userId);
        return repository.getLogsForBorrowedBooksByUserId(userId);
    }

    //TODO replace with AuthUser after adding Security
    @GetMapping("/lent/history")
    public List<BorrowLogDTOLent> getLogsForLentBooks(@RequestParam int userId) {
        log.info("getInfoAboutLentBooksForUserWithId {}", userId);
        return repository.getLogsForLentBooksByUserId(userId);
    }

    //TODO replace with AuthUser after adding Security
    @PostMapping(value = "/borrow")
    public ResponseEntity<BorrowLog> createWithLocation(@RequestParam int userId, @RequestParam LocalDate deadline,
                                                        @RequestParam int bookId) {
        log.info("create new borrowLog");
        BorrowLog created = service.save(userId, deadline, bookId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
