package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chupaYchups.dao.AuthorDao;
import ru.chupaYchups.dao.BookDao;
import ru.chupaYchups.dao.GenreDao;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
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
    public List<BookDto> findBooks(Optional<String> authorNameOptional, Optional<String> genreNameOptional, Optional<String> nameOptional) {
        Optional<Author> authorOptional = authorNameOptional.flatMap(authorName -> authorDao.findByName(authorName));
        Optional<Genre> genreOptional = genreNameOptional.flatMap(genreName -> genreDao.findByName(genreName));
        return bookDao.findBooks(authorOptional, genreOptional, nameOptional).
            stream().
            map(book -> new BookDto(book.getId(), book.getName(), book.getAuthor().getName(), book.getGenre().getName())).
            collect(Collectors.toList());
    }

    @Override
    public void addBook(String name, String authorName, String genreName) {
        Author author = authorDao.findByName(authorName).orElseGet(() -> authorDao.insert(new Author(authorName)));
        Genre genre = genreDao.findByName(genreName).orElseGet(() -> genreDao.insert(new Genre(genreName)));
        bookDao.insert(new Book(name, author, genre));
    }

    @Override
    public void updateBookById(long id, Optional<String> nameOptional, Optional<String> authorNameOptional, Optional<String> genreNameOptional) {
        Book book = bookDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find book with id: " + id));
        nameOptional.ifPresent(name -> book.setName(name));
        authorNameOptional.ifPresent(authorName -> book.setAuthor(authorDao.findByName(authorName).
            orElseGet(() -> authorDao.insert(new Author(authorName)))));
        genreNameOptional.ifPresent(genreName -> book.setGenre(genreDao.findByName(genreName).
            orElseGet(() -> genreDao.insert(new Genre(genreName)))));
        bookDao.update(book);
    }

    @Override
    public void deleteBookById(long id) {
        Book book = bookDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find book with id: " + id));
        bookDao.delete(book);
    }
}
