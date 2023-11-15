package com.example.booksharingapp.service;

import com.example.booksharingapp.exception.IllegalRequestDataException;
import com.example.booksharingapp.repository.BorrowLogRepository;
import com.example.booksharingapp.repository.UserRepository;
import com.example.booksharingapp.to.BorrowLogDTOBorrowed;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;

    private BorrowLogRepository borrowLogRepository;

    @Transactional
    public void delete(int userId) {
        List<BorrowLogDTOBorrowed> booksNotReturned = borrowLogRepository.getLogsForBorrowedBooksByUserIdNotReturned(userId);
        if (booksNotReturned.isEmpty()) {
            repository.deleteExisted(userId);
        } else {
            throw new IllegalRequestDataException("You should return all books before deleting a profile.");
        }
    }
}
