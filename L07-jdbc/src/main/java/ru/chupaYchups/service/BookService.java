package ru.chupaYchups.service;

import ru.chupaYchups.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllBooks(Optional<String> authorNameOptional, Optional<String> genreNameOptional, Optional<String> nameOptional);
}
