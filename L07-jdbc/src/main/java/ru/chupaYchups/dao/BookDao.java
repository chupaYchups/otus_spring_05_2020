package ru.chupaYchups.dao;

import ru.chupaYchups.domain.Book;

public interface BookDao {

    void insert(Book book);

    Book findById(Long id);

    void delete(Book book);

    void update(Book book);
}
