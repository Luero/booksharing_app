package com.example.booksharingapp.util;

import com.example.booksharingapp.model.Book;
import com.example.booksharingapp.to.BookTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ToUtil {

    public static Book createFromBookTo(BookTo bookTo) {
        return new Book(null, null, bookTo.getAuthor(), bookTo.getName(), bookTo.getYearOfCurrentEdition(),
                bookTo.getDescription(), bookTo.getLanguage(), true);
    }

    public static Book updateFromBookTo(Book book, BookTo bookTo) {
        book.setAuthor(bookTo.getAuthor());
        book.setName(bookTo.getName());
        book.setDescription(bookTo.getDescription());
        book.setYearOfCurrentEdition(bookTo.getYearOfCurrentEdition());
        book.setLanguage(bookTo.getLanguage());
        return book;
    }
}
