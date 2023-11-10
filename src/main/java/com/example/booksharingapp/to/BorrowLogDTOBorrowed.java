package com.example.booksharingapp.to;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowLogDTOBorrowed extends BorrowLogDTO {

    @NotBlank
    private String ownerName;

    public BorrowLogDTOBorrowed(String bookName, String bookAuthor,
                                LocalDate borrowDate, LocalDate deadline,
                                boolean isReturned, boolean wasExpired, String ownerName) {
        super(bookName, bookAuthor, borrowDate, deadline, isReturned, wasExpired);
        this.ownerName = ownerName;
    }
}
