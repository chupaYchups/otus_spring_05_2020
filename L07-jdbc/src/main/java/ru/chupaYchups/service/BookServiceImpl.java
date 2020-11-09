package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.chupaYchups.dao.AuthorDao;
import ru.chupaYchups.dao.BookDao;
import ru.chupaYchups.dao.GenreDao;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public List<Book> getAllBooks(Optional<String> authorNameOptional, Optional<String> genreNameOptional) {
        Author author;
        Genre genre;

        try {
            author = authorNameOptional.map(authorName -> authorDao.findByName(authorName)).orElseThrow(IllegalAccessException);
        } catch (DataAccessException dae) {
            throw new IllegalArgumentException("Cannot find author with name : " + authorName);
        }
        try {
            genre = genreDao.findByName(genreName);
        } catch (DataAccessException dae) {
            throw new IllegalArgumentException("Cannot find genre with name : " + genreName);
        }
        return bookDao.getByAuthorAndGenre(author.getId(), genre.getId());
    }
}
