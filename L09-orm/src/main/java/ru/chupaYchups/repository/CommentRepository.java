package ru.chupaYchups.repository;

import ru.chupaYchups.domain.Comment;

public interface CommentRepository {
    Comment save(Comment comment);
    Comment findById(Long id);
    void delete(Comment comment);
}
