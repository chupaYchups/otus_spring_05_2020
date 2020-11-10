package ru.chupaYchups.dao;

import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    long insert(Book book);

    Optional<Book> findById(Long id);

    void delete(Book book);

    void update(Book book);

    List<Book> getAll();

    List<Book> getByAuthorAndGenre(Optional<Author> authorOptional, Optional<Genre> genreOptional);
}
