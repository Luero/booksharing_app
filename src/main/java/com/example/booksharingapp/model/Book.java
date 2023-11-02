package com.example.booksharingapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @NotBlank
    @Column(name = "author", nullable = false)
    private String author;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "year_of_current_edition")
    private int yearOfCurrentEdition;

    @Size(max = 300)
    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "language")
    private String language;

    @NotNull
    @Column(name = "availability", nullable = false, columnDefinition = "bool default true")
    private boolean availability;

    public Book(Integer id, User owner, String author, String name,
                int yearOfCurrentEdition, String description, String language, boolean availability) {
        super(id);
        this.owner = owner;
        this.author = author;
        this.name = name;
        this.yearOfCurrentEdition = yearOfCurrentEdition;
        this.description = description;
        this.language = language;
        this.availability = availability;
    }
}
