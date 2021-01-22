package ru.chupaYchups.service;

import ru.chupaYchups.dto.BookDto;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> findBooks(Optional<String> authorNameOptional, Optional<String> genreNameOptional, Optional<String> nameOptional);
    void addBook(String name, String authorName, String genreName);
    void updateBookById(String id, Optional<String> namOptional, Optional<String> authorName, Optional<String> genreName);
    void deleteBookById(String id);
    List<BookDto> getAllBooks();
}
