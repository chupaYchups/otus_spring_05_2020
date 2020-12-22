package ru.chupaYchups.repository;

public interface BookRepositoryCustom {
    void removeByAuthorId(String authorId);
    void removeByGenreId(String authorId);
    void removeCommentRefWhereExist(String commentId);

    long getCommentsArrayLengthById(String bookId);
}
