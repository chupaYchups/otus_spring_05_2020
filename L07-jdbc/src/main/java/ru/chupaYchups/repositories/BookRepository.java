package ru.chupaYchups.repositories;

import ru.chupaYchups.domain.Book;

public interface BookRepository {

    void insert(Book book);

    Book findById(Long id);

    void delete(Book book);

    void update(Book book);
}
