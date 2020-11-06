package ru.chupaYchups.repositories;

import ru.chupaYchups.domain.Genre;

public interface GenreRepository {

    void insert(Genre genre);

    Genre findById(Long id);

    void delete(Genre book);

    void update(Genre book);
}
