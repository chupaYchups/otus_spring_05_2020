package ru.chupaYchups.repository;

import ru.chupaYchups.domain.Book;

public interface BookRepositoryCustom {
    Book findBookById(Long id);
}
