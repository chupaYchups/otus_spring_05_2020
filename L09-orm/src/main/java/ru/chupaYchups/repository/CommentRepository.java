package ru.chupaYchups.repository;

import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    void delete(Comment comment);
    List<Comment> findByBook(Book book);
}
