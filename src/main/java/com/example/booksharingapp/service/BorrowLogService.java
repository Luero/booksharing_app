package com.example.booksharingapp.service;

import com.example.booksharingapp.exception.IllegalRequestDataException;
import com.example.booksharingapp.exception.NotFoundException;
import com.example.booksharingapp.model.Book;
import com.example.booksharingapp.model.BorrowLog;
import com.example.booksharingapp.model.User;
import com.example.booksharingapp.repository.BookRepository;
import com.example.booksharingapp.repository.BorrowLogRepository;
import com.example.booksharingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BorrowLogService {

    private BorrowLogRepository borrowLogRepository;

    private BookRepository bookRepository;

    private UserRepository userRepository;

    @Transactional
    public BorrowLog save(int userId, LocalDate deadline, int bookId) {
        Book book = bookRepository.getExisted(bookId);
        if (book.isAvailable()) {
            Optional<User> ownerOptional = userRepository.getUserByBookId(bookId);
            if (ownerOptional.isEmpty()) {
                throw new NotFoundException("No owner is found for this book");
            } else {
                User owner = ownerOptional.get();
                User borrower = userRepository.getExisted(userId);
                LocalDate borrowDate = LocalDate.now();
                BorrowLog borrowLog = new BorrowLog(null, book, owner, borrower, borrowDate, deadline);
                book.setAvailable(false);
                bookRepository.save(book);
                return borrowLogRepository.save(borrowLog);
            }
        } else {
            throw new IllegalRequestDataException("This book is not available");
        }
    }
}
