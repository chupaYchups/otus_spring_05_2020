package ru.chupaYchups.dao;

import ru.chupaYchups.domain.Author;

import java.util.List;

public interface AuthorDao {

    long insert(Author author);

    Author findById(Long id);

    Author findByName(String name);

    void deleteById(Long id);

    void update(Author author);

    List<Author> getAll();
}
