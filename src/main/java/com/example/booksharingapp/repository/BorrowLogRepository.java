package com.example.booksharingapp.repository;

import com.example.booksharingapp.model.BorrowLog;
import com.example.booksharingapp.to.BorrowLogDTOBorrowed;
import com.example.booksharingapp.to.BorrowLogDTOLent;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowLogRepository extends BaseRepository<BorrowLog> {

    @Query("SELECT new com.example.booksharingapp.to.BorrowLogDTOBorrowed(l.book.name, l.book.author," +
            " l.borrowDate, l.deadline, l.isReturned, l.wasExpired, l.owner.name)" +
            " FROM BorrowLog l WHERE l.borrower.id=:id")
    List<BorrowLogDTOBorrowed> getLogsForBorrowedBooksByUserId(int id);

    @Query("SELECT new com.example.booksharingapp.to.BorrowLogDTOLent(l.book.name, l.book.author," +
            " l.borrowDate, l.deadline, l.isReturned, l.wasExpired, l.borrower.name)" +
            " FROM BorrowLog l WHERE l.owner.id=:id")
    List<BorrowLogDTOLent> getLogsForLentBooksByUserId(int id);

    @Query("SELECT new com.example.booksharingapp.to.BorrowLogDTOBorrowed(l.book.name, l.book.author," +
            " l.borrowDate, l.deadline, l.isReturned, l.wasExpired, l.owner.name)" +
            " FROM BorrowLog l WHERE l.borrower.id=:id AND l.isReturned=false")
    List<BorrowLogDTOBorrowed> getLogsForBorrowedBooksByUserIdNotReturned(int id);

    @Query("SELECT new com.example.booksharingapp.to.BorrowLogDTOLent(l.book.name, l.book.author," +
            " l.borrowDate, l.deadline, l.isReturned, l.wasExpired, l.borrower.name)" +
            " FROM BorrowLog l WHERE l.owner.id=:id AND l.isReturned=false")
    List<BorrowLogDTOLent> getLogsForLentBooksByUserIdNotReturned(int id);
}
