package ru.chupaYchups.dao;

import ru.chupaYchups.domain.Author;

public interface AuthorDao {

    void insert(Author author);

    Author findById(Long id);

    void delete(Author author);

    void update(Author author);
}
