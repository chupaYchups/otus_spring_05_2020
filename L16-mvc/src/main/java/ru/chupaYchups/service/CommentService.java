package ru.chupaYchups.service;

import ru.chupaYchups.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getBookComments(long bookId);
    void addComment(Long bookId, String commentText);
    void deleteComment(Long commentId);
    void updateComment(Long commentId, String text);
}
