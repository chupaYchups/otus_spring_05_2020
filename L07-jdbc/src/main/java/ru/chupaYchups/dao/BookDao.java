package ru.chupaYchups.dao;

import ru.chupaYchups.domain.Book;

import java.util.List;

public interface BookDao {

    long insert(Book book);

    Book findById(Long id);

    void deleteById(Long id);

    void update(Book book);

    List<Book> getAll();

    List<Book> getByAuthorAndGenre(Long authorId, Long genreId);
}
