package ru.chupaYchups.dao;

import ru.chupaYchups.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    Author insert(Author author);

    Author findById(Long id);

    Optional<Author> findByName(String name);

    void deleteById(Long id);

    void update(Author author);

    List<Author> getAll();
}
