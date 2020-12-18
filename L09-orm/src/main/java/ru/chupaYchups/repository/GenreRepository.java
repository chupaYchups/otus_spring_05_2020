package ru.chupaYchups.repository;

import ru.chupaYchups.domain.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre genre);
    Optional<Genre> findById(Long id);
    Optional<Genre> findByName(String name);
    List<Genre> getAll();
    void delete(Genre genre);
}
