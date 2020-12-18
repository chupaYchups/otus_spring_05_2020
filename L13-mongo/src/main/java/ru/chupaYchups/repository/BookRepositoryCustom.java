package ru.chupaYchups.repository;

public interface BookRepositoryCustom {
    void removeAuthorBooks(String authorId);
    void removeGenreBooks(String authorId);
    void clearCommentInfoFromBooks(String commentId);
}
