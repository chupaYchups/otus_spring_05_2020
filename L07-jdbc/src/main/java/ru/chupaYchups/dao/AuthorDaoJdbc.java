package ru.chupaYchups.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

@SuppressWarnings("SqlResolve")
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("name"));
        }
    }


    private final NamedParameterJdbcOperations jdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public long insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorName", author.getName());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbcOperations.update("insert into Author(name) values(:authorName)", params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public Author findById(Long id) {
        return jdbcOperations.queryForObject("select a.id, a.name from author where id = :id",
            Map.of("id", id), new AuthorRowMapper());
    }

    @Override
    public Author findByName(String name) {
        return jdbcOperations.queryForObject("select a.id, a.name from author a where a.name = :name",
                Map.of("name", name), new AuthorRowMapper());
    }

    @Override
    public void deleteById(Long id) {
        jdbcOperations.update("delete from author where a.id = :id",
            Map.of("id", id));
    }

    @Override
    public void update(Author author) {
        jdbcOperations.update("update author a set a.name = :name",
            Map.of("id", author.getId(), "name", author.getName()));
    }
}
