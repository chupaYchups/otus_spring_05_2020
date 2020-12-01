package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;
import ru.chupaYchups.dto.CommentDto;
import ru.chupaYchups.repository.BookRepository;
import ru.chupaYchups.repository.CommentRepository;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceJpa implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public List<CommentDto> getBookComments(long bookId) {
        Book book = bookRepository.findById(bookId).
            orElseThrow(() -> new IllegalArgumentException("Cannot find book with id " + bookId));
        return commentRepository.findByBook(book).stream().
            map(comment -> new CommentDto(comment.getCommentString())).
                collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).
            orElseThrow(() -> new IllegalArgumentException("Cannot find comment with id " + commentId));
        commentRepository.delete(comment);
    }

    @Override
    public void addComment(Long bookId, String commentText) {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new IllegalArgumentException("Cannot find book with id " + bookId));
        commentRepository.save(new Comment(commentText, book));
    }

    @Override
    public void updateComment(Long commentId, String text) {
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new IllegalArgumentException("Cannot find comment with id " + commentId));
        comment.setCommentString(text);
        commentRepository.save(comment);
    }
}
