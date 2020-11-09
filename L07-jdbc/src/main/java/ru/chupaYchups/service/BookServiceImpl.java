package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.chupaYchups.dao.AuthorDao;
import ru.chupaYchups.dao.BookDao;
import ru.chupaYchups.dao.GenreDao;
import ru.chupaYchups.domain.Book;
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
        Optional<Long> authorIdOptional = Optional.empty();
        Optional<Long> genreIdOptional = Optional.empty();
        try {
            authorIdOptional = authorNameOptional.isPresent() ?
                    authorNameOptional.map(authorName -> authorDao.findByName(authorName).getId()) :
                    Optional.empty();
        } catch (DataAccessException dae) {
            authorNameOptional.ifPresent(s -> {
                throw new IllegalArgumentException("Cannot find author with name : " + s, dae);
            });
        }
        try {
            genreIdOptional = genreNameOptional.isPresent() ?
                    genreNameOptional.map(authorName -> authorDao.findByName(authorName).getId()) :
                    Optional.empty();
        } catch (DataAccessException dae) {
            genreNameOptional.ifPresent(s -> {
                throw new IllegalArgumentException("Cannot find genre with name : " + s, dae);
            });
        }
        return bookDao.getByAuthorAndGenre(authorIdOptional, genreIdOptional);
    }
}
