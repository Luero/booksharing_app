package com.example.booksharingapp.to;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowLogDTOLent extends BorrowLogDTO {

    @NotBlank
    private String borrowerName;

    public BorrowLogDTOLent(String bookName, String bookAuthor,
                            LocalDate borrowDate, LocalDate deadline,
                            boolean isReturned, boolean wasExpired, String borrowerName) {
        super(bookName, bookAuthor, borrowDate, deadline, isReturned, wasExpired);
        this.borrowerName = borrowerName;
    }
}
