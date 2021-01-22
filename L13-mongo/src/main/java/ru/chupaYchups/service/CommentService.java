package ru.chupaYchups.service;

import ru.chupaYchups.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getBookComments(String bookId);
    void addComment(String bookId, String commentText);
    void deleteComment(String commentId);
    void updateComment(String commentId, String text);
}
