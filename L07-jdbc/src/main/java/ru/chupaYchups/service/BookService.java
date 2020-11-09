package ru.chupaYchups.service;

import ru.chupaYchups.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks(Optional<String> authorNameOptional, Optional<String> genreNameOptional);
}
