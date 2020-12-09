package ru.chupaYchups.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByNameAndAuthorAndGenre(Optional<String> nameOptional, Optional<Author> authorOptional, Optional<Genre> genreOptional);
}
