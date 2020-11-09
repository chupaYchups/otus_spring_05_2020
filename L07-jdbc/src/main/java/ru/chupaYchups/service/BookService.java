package ru.chupaYchups.service;

import ru.chupaYchups.domain.Book;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks(String authorName, String genreName);
}
