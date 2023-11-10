package com.example.booksharingapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "borrow_log")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowLog extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    @NotNull
    @Column(name = "borrow_date", nullable = false, updatable = false)
    private LocalDate borrowDate;

    @NotNull
    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @NotNull
    @Column(name = "is_returned", nullable = false, columnDefinition = "bool default false")
    private boolean isReturned;

    @NotNull
    @Column(name = "was_expired", nullable = false, columnDefinition = "bool default false")
    private boolean wasExpired;

    public BorrowLog(Integer id, Book book, User owner, User borrower, LocalDate borrowDate, LocalDate deadline) {
        super(id);
        this.book = book;
        this.owner = owner;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
        this.deadline = deadline;
        this.isReturned = false;
        this.wasExpired = false;
    }
}
