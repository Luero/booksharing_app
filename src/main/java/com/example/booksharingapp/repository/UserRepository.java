package com.example.booksharingapp.repository;

import com.example.booksharingapp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.booksharingapp.config.SecurityConfig.PASSWORD_ENCODER;

public interface UserRepository extends BaseRepository<User>{

    @Query("SELECT u FROM User u LEFT JOIN u.booksToLend b WHERE b.id=:bookId")
    Optional<User> getUserByBookId(int bookId);

    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(String email);

    @Transactional
    default User prepareAndSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return save(user);
    }
}
