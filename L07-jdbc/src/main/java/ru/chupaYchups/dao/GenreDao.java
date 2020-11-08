package ru.chupaYchups.dao;

import ru.chupaYchups.domain.Genre;

public interface GenreDao {

    long insert(Genre genre);

    Genre findById(Long id);

    Genre findByName(String name);

    void delete(Long id);

    void update(Genre book);
}
