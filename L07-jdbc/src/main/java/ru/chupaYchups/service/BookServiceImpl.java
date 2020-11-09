package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.chupaYchups.dao.AuthorDao;
import ru.chupaYchups.dao.BookDao;
import ru.chupaYchups.dao.GenreDao;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Genre;
import ru.chupaYchups.dto.BookDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public List<BookDto> getAllBooks(Optional<String> authorNameOptional, Optional<String> genreNameOptional, Optional<String> nameOptional) {

        Optional<Author> authorOptional = authorNameOptional.map(authorName -> {
            try {
                return authorDao.findByName(authorName);
            } catch (DataAccessException dae) {
                throw new IllegalArgumentException("Cannot find author with name : " + authorName, dae);
            }
        });

        Optional<Genre> genreOptional = genreNameOptional.map(genreName -> {
            try {
                return genreDao.findByName(genreName);
            } catch (DataAccessException dae) {
                throw new IllegalArgumentException("Cannot find genre with name : " + genreName, dae);
            }
        });

        return bookDao.getByAuthorAndGenre(authorOptional, genreOptional).
            stream().
            map(book -> new BookDto(book.getName(), book.getAuthor().getName(), book.getGenre().getName())).
            collect(Collectors.toList());
    }
}
