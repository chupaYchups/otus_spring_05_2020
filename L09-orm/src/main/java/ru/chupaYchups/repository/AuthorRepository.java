package ru.chupaYchups.repository;

import ru.chupaYchups.domain.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);
    Optional<Author> findById(Long id);
    Optional<Author> findByName(String name);
    List<Author> getAll();
    void delete(Author author);
}
