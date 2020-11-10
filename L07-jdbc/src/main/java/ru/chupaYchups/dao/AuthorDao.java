package ru.chupaYchups.dao;

import ru.chupaYchups.domain.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Author insert(Author author);
    Author findById(Long id);
    Optional<Author> findByName(String name);
    List<Author> getAll();
}
