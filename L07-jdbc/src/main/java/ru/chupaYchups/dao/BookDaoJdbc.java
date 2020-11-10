package ru.chupaYchups.dao;

import org.springframework.dao.EmptyResultDataAccessException;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Repository
public class BookDaoJdbc implements BookDao {

    private final static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getLong("id"),
                rs.getString("name"),
                new Author(rs.getLong("author_id"), rs.getString("author_name")),
                new Genre(rs.getLong("genre_id"), rs.getString("genre_name")));
        }
    }

    private final NamedParameterJdbcOperations jdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("bookName", book.getName());
        params.addValue("authorId", book.getAuthor().getId());
        params.addValue("genreId", book.getGenre().getId());

        KeyHolder kh = new GeneratedKeyHolder();
        jdbcOperations.update("insert into book(name, author_id, genre_id) " +
                "values(:bookName, :authorId, :genreId)", params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public Optional<Book> findById(Long id) {
        try {
            return Optional.of(jdbcOperations.queryForObject("select " +
                    "b.id, b.name, " +
                    "b.author_id, b.genre_id, " +
                    "a.name author_name, g.name genre_name  from book b " +
                    "inner join author a on b.author_id = a.id " +
                    "inner join genre g on b.genre_id = g.id " +
                    "where b.id = :id", Map.of("id", id), new BookRowMapper()));
        } catch (EmptyResultDataAccessException dae) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Book book) {
        jdbcOperations.update("delete from book where id = :id", Map.of("id", book.getId()));
    }

    @Override
    public void update(Book book) {
        jdbcOperations.update("update book " +
            "set name = :bookName, " +
            "author_id = :authorId, " +
            "genre_id = :genreId " +
            "where id = :id",
            Map.of("bookName", book.getName(),
                "authorId", book.getAuthor().getId(),
                "genreId", book.getGenre().getId(),
                "id", book.getId()));
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query("select " +
            "b.id, b.name, " +
            "b.author_id, b.genre_id, " +
            "a.name author_name, g.name genre_name  from book b " +
            "inner join author a on b.author_id = a.id " +
            "inner join genre g on b.genre_id = g.id", new BookRowMapper());
    }

    @Override
    public List<Book> getByAuthorAndGenre(Optional<Author> authorOptional, Optional<Genre> genreOptional) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("authorId", authorOptional.map(author -> author.getId()).orElse(null));
        sqlParameterSource.addValue("genreId", genreOptional.map(genre -> genre.getId()).orElse(null));
        return jdbcOperations.query("select " +
                "b.id, b.name, " +
                "b.author_id, b.genre_id, " +
                "a.name author_name, g.name genre_name  from book b " +
                "inner join author a on b.author_id = a.id " +
                "inner join genre g on b.genre_id = g.id " +
                "where a.id = nvl(:authorId, a.id)" +
                "and g.id = nvl(:genreId, g.id)",
                sqlParameterSource,
                new BookRowMapper());
    }
}
