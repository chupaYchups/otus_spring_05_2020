package ru.chupaYchups.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    private final static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("name"));
        }
    }

    @Override
    public Author insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorName", author.getName());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbcOperations.update("insert into Author(name) values(:authorName)", params, kh);
        return findById(kh.getKey().longValue());
    }

    @Override
    public Author findById(Long id) {
        return jdbcOperations.queryForObject("select a.id, a.name from author a where id = :id",
            Map.of("id", id), new AuthorRowMapper());
    }

    @Override
    public Optional<Author> findByName(String name) {
        try {
            return Optional.of(
                jdbcOperations.queryForObject("select a.id," +
                " a.name " +
                "from author a " +
                "where a.name = :name",
                Map.of("name", name),
                new AuthorRowMapper()));
        } catch (EmptyResultDataAccessException dae) {
            //todo debug
            return Optional.empty();
        }
    }

    @Override
    public List<Author> getAll() {
        return jdbcOperations.query("select a.id, a.name from author a", new AuthorRowMapper());
    }
}
