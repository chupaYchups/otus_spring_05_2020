package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chupaYchups.dto.CommentDto;
import ru.chupaYchups.repository.AuthorRepository;
import ru.chupaYchups.repository.BookRepository;
import ru.chupaYchups.repository.GenreRepository;
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

    private final BookRepository bookDao;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findBooks(Optional<String> authorNameOptional, Optional<String> genreNameOptional, Optional<String> nameOptional) {
        Optional<Author> authorOptional = authorNameOptional.flatMap(authorName -> authorRepository.findByName(authorName));
        Optional<Genre> genreOptional = genreNameOptional.flatMap(genreName -> genreRepository.findByName(genreName));
        return bookDao.findBooks(authorOptional, genreOptional, nameOptional).
            stream().
            map(book -> new BookDto(book.getId(),
                book.getName(),
                book.getAuthor().getName(),
                book.getGenre().getName(),
                book.getComments().stream().
                    map(comment -> new CommentDto(comment.getId(), comment.getCommentString())).
                    collect(Collectors.toList())
                )).
            collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addBook(String name, String authorName, String genreName) {
        Author author = authorRepository.findByName(authorName).orElseGet(() -> authorRepository.save(new Author(authorName)));
        Genre genre = genreRepository.findByName(genreName).orElseGet(() -> genreRepository.save(new Genre(genreName)));
        bookDao.save(new Book(name, author, genre));
    }

    @Override
    @Transactional
    public void updateBookById(long id, Optional<String> nameOptional, Optional<String> authorNameOptional, Optional<String> genreNameOptional) {
        Book book = bookDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find book with id: " + id));
        nameOptional.ifPresent(name -> book.setName(name));
        authorNameOptional.ifPresent(authorName -> book.setAuthor(authorRepository.findByName(authorName).
            orElseGet(() -> authorRepository.save(new Author(authorName)))));
        genreNameOptional.ifPresent(genreName -> book.setGenre(genreRepository.findByName(genreName).
            orElseGet(() -> genreRepository.save(new Genre(genreName)))));
        bookDao.save(book);
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        Book book = bookDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find book with id: " + id));
        bookDao.delete(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks() {
        return bookDao.getAllBooks().
                stream().
                map(book -> new BookDto(book.getId(),
                    book.getName(), book.getAuthor().getName(),
                    book.getGenre().getName(),
                    book.getComments().stream().
                        map(comment -> new CommentDto(comment.getId(), comment.getCommentString())).
                        collect(Collectors.toList()))).
                collect(Collectors.toList());
    }
}
