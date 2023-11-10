package com.example.booksharingapp.to;

import com.example.booksharingapp.HasId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowLogDTO implements HasId {

//TODO think about necessity of HasId here

    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // https://stackoverflow.com/a/28025008/548473
    protected Integer id;

    @NotBlank
    private String bookName;

    @NotBlank
    private String bookAuthor;

    @NotNull
    private LocalDate borrowDate;

    @NotNull
    private LocalDate deadline;

    @NotNull
    private boolean isReturned;

    @NotNull
    private boolean wasExpired;

    public BorrowLogDTO(String bookName, String bookAuthor,
                        LocalDate borrowDate, LocalDate deadline,
                        boolean isReturned, boolean wasExpired) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.borrowDate = borrowDate;
        this.deadline = deadline;
        this.isReturned = isReturned;
        this.wasExpired = wasExpired;
    }
}
