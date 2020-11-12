package ru.chupaYchups.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    private final static class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getLong("id"), rs.getString("name"));
        }
    }

    @Override
    public Genre insert(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("genreName", genre.getName());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbcOperations.update("insert into Genre(name) values(:genreName)",
                params, kh);
        return findById(kh.getKey().longValue());
    }

    @Override
    public Genre findById(Long id) {
        return jdbcOperations.queryForObject("select g.id, g.name from Genre g where id = :id", Map.of("id", id), new GenreRowMapper());
    }

    @Override
    public Optional<Genre> findByName(String name) {
        try {
            return Optional.of(
                    jdbcOperations.queryForObject(
                            "select g.id, " +
                                    "g.name " +
                                    "from Genre g " +
                                    "where name = :name",
                            Map.of("name", name),
                            new GenreRowMapper()));
        } catch (EmptyResultDataAccessException dae) {
            return Optional.empty();
        }
    }

    @Override
    public List<Genre> getAll() {
        return jdbcOperations.query("select g.id, g.name from genre g", new GenreRowMapper());
    }
}
