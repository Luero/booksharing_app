package com.example.booksharingapp.to;

import com.example.booksharingapp.HasId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BookTo implements HasId {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // https://stackoverflow.com/a/28025008/548473
    protected Integer id;

    @NotBlank
    private String author;

    @NotBlank
    private String name;

    @NotNull
    private int yearOfCurrentEdition;

    @Size(max = 300)
    private String description;

    @NotBlank
    private String language;
}
