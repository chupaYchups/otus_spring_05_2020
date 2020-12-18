package ru.chupaYchups.repository;

import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.repository.exception.NoSuchBookException;

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private BookRepository bookRepository;

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException(id));
    }
}
