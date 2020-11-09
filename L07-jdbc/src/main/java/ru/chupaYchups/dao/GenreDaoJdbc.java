package ru.chupaYchups.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("SqlResolve")
@Repository
public class GenreDaoJdbc implements GenreDao {

    private final static class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getLong("id"), rs.getString("name"));
        }
    }

    private final NamedParameterJdbcOperations jdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public long insert(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("genreName", genre.getName());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbcOperations.update("insert into Genre(name) values(:genreName)",
                params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public Genre findById(Long id) {
        return jdbcOperations.queryForObject("select g.id, g.name from Genre g where id = :id", Map.of("id", id), new GenreRowMapper());
    }

    @Override
    public Genre findByName(String name) {
        return jdbcOperations.queryForObject("select g.id, g.name from Genre g where name = :name", Map.of("name", name), new GenreRowMapper());
    }

    @Override
    public void delete(Long id) {
        jdbcOperations.update("delete from Genre where id = :id", Map.of("id", id));
    }

    @Override
    public void update(Genre genre) {
        jdbcOperations.update("update Genre g set g.name = :name", Map.of("genreName", genre.getName()));
    }

    @Override
    public List<Genre> getAll() {
        return jdbcOperations.query("select g.id, g.name from genre g", new GenreRowMapper());
    }
}
