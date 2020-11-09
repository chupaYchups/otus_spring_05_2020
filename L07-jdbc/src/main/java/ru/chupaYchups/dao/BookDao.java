package ru.chupaYchups.dao;

import ru.chupaYchups.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    long insert(Book book);

    Book findById(Long id);

    void deleteById(Long id);

    void update(Book book);

    List<Book> getAll();

    List<Book> getByAuthorAndGenre(Optional<Long> authorId, Optional<Long> genreId);
}
