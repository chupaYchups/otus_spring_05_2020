package ru.chupaYchups.repositories;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.chupaYchups.domain.Author;

public class AuthorRepositoryJdbc implements AuthorRepository {

    private NamedParameterJdbcOperations jdbcOperations;

    @Override
    public void insert(Author author) {

    }

    @Override
    public Author findById(Long id) {
        return null;
    }

    @Override
    public void delete(Author author) {

    }

    @Override
    public void update(Author author) {

    }
}
