package ru.chupaYchups.dao;

import ru.chupaYchups.domain.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Genre insert(Genre genre);
    Genre findById(Long id);
    Optional<Genre> findByName(String name);
    List<Genre> getAll();
}
