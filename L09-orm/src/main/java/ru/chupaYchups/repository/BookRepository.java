package ru.chupaYchups.repository;

import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(Long id);
    void delete(Book book);
    List<Book> findBooks(Optional<Author> authorOptional, Optional<Genre> genreOptional, Optional<String> nameOptional);
    List<Book> getAllBooks();
}
