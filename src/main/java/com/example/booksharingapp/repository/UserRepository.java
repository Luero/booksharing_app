package com.example.booksharingapp.repository;

import com.example.booksharingapp.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User>{

    @Query("SELECT u FROM User u LEFT JOIN u.booksToLend b WHERE b.id=:bookId")
    Optional<User> getUserByBookId(int bookId);
}
