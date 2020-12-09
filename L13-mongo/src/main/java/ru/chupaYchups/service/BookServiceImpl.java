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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public static class BookDtoMapper implements Function<Book, BookDto> {
        @Override
        public BookDto apply(Book book) {
            return new BookDto(book.getId(),
                    book.getName(),
                    book.getAuthor().getName(),
                    book.getGenre().getName(),
                    book.getComments() != null ?
                        book.getComments().stream().
                            map(new CommentServiceImpl.CommentDtoMapper()).
                            collect(Collectors.toList())
                        : Collections.emptyList());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findBooks(Optional<String> authorNameOptional, Optional<String> genreNameOptional, Optional<String> nameOptional) {
        Optional<Author> authorOptional = authorNameOptional.flatMap(authorName -> authorRepository.findByName(authorName));
        Optional<Genre> genreOptional = genreNameOptional.flatMap(genreName -> genreRepository.findByName(genreName));
        return bookRepository.findAllByAuthorOrGenreOrName(nameOptional.get(),authorOptional.get(), genreOptional.get()).
            stream().
            map(new BookDtoMapper()).
            collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addBook(String name, String authorName, String genreName) {
        Author author = authorRepository.findByName(authorName).orElseGet(() -> authorRepository.save(new Author(authorName)));
        Genre genre = genreRepository.findByName(genreName).orElseGet(() -> genreRepository.save(new Genre(genreName)));
        bookRepository.save(new Book(name, author, genre));
    }

    @Override
    @Transactional
    public void updateBookById(long id, Optional<String> nameOptional, Optional<String> authorNameOptional, Optional<String> genreNameOptional) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find book with id: " + id));
        nameOptional.ifPresent(name -> book.setName(name));
        authorNameOptional.ifPresent(authorName -> book.setAuthor(authorRepository.findByName(authorName).
            orElseGet(() -> authorRepository.save(new Author(authorName)))));
        genreNameOptional.ifPresent(genreName -> book.setGenre(genreRepository.findByName(genreName).
            orElseGet(() -> genreRepository.save(new Genre(genreName)))));
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find book with id: " + id));
        bookRepository.delete(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).
                map(new BookDtoMapper()).
                collect(Collectors.toList());
    }
}
