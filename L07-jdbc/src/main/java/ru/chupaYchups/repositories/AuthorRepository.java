package ru.chupaYchups.repositories;

import ru.chupaYchups.domain.Author;

public interface AuthorRepository {

    void insert(Author author);

    Author findById(Long id);

    void delete(Author author);

    void update(Author author);
}
