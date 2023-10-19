package com.example.booksharingapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "contacts"},
        name = "users_unique_email_contacts_idx"))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 256)
    // https://stackoverflow.com/a/12505165/548473
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank
    @Column(name = "contacts", nullable = false)
    private String contacts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    @OrderBy("author")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Book> booksToLend;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "borrower")
    @OrderBy("deadline DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<BorrowLog> borrowedBooks;

    @Override
    public String toString() {
        return super.toString() + '[' + name + ']';
    }

    public User(Integer id, String name, String email, String password, String contacts) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.contacts = contacts;
    }
}
