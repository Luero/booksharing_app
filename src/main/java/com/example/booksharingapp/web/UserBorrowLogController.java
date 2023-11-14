package com.example.booksharingapp.web;

import com.example.booksharingapp.AuthUser;
import com.example.booksharingapp.model.BorrowLog;
import com.example.booksharingapp.repository.BorrowLogRepository;
import com.example.booksharingapp.service.BorrowLogService;
import com.example.booksharingapp.to.BorrowLogDTOBorrowed;
import com.example.booksharingapp.to.BorrowLogDTOLent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/borrowed")
    public List<BorrowLogDTOBorrowed> getLogsForBorrowedBooksNotReturned(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getInfoAboutBorrowedBooksNotReturnedForUserWithId {}", authUser.id());
        return repository.getLogsForBorrowedBooksByUserIdNotReturned(authUser.id());
    }

    @GetMapping("/lent")
    public List<BorrowLogDTOLent> getLogsForLentBooksNotReturned(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getInfoAboutLentBooksNotReturnedForUserWithId {}", authUser.id());
        return repository.getLogsForLentBooksByUserIdNotReturned(authUser.id());
    }

    @GetMapping("/borrowed/history")
    public List<BorrowLogDTOBorrowed> getLogsForBorrowedBooks(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getInfoAboutBorrowedBooksForUserWithId {}", authUser.id());
        return repository.getLogsForBorrowedBooksByUserId(authUser.id());
    }

    @GetMapping("/lent/history")
    public List<BorrowLogDTOLent> getLogsForLentBooks(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getInfoAboutLentBooksForUserWithId {}", authUser.id());
        return repository.getLogsForLentBooksByUserId(authUser.id());
    }

    @PostMapping(value = "/borrow")
    public ResponseEntity<BorrowLog> createWithLocation(@AuthenticationPrincipal AuthUser authUser,
                                                        @RequestParam LocalDate deadline,
                                                        @RequestParam int bookId) {
        log.info("create new borrowLog");
        BorrowLog created = service.create(authUser.id(), deadline, bookId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/accept-return/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReturnStatus(@PathVariable int id, @RequestParam boolean isReturned) {
        log.info("update borrow log with id={} by changing return status", id);
        service.updateReturnStatus(id, isReturned);
    }
}
