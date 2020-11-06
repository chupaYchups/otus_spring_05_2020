package ru.chupaYchups.dao;

import ru.chupaYchups.domain.Genre;

public interface GenreDao {

    void insert(Genre genre);

    Genre findById(Long id);

    void delete(Genre book);

    void update(Genre book);
}
