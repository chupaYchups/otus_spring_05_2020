package ru.chupaYchups.repository;

import ru.chupaYchups.domain.Comment;

public interface CommentRepositoryCustom {
    Comment findCommentById(Long id);
}
