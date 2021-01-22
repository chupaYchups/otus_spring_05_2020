package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;
import ru.chupaYchups.dto.CommentDto;
import ru.chupaYchups.repository.BookRepository;
import ru.chupaYchups.repository.CommentRepository;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    static class CommentDtoMapper implements Function<Comment, CommentDto> {
        @Override
        public CommentDto apply(Comment comment) {
            return new CommentDto(comment.getId(), comment.getCommentString());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getBookComments(String bookId) {
        Book book = bookRepository.findById(bookId).
            orElseThrow(() -> new IllegalArgumentException("Cannot find book with id " + bookId));
        return book.getComments().
            stream().
            map(new CommentDtoMapper()).
            collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteComment(String commentId) {
        Comment comment = commentRepository.findById(commentId).
            orElseThrow(() -> new IllegalArgumentException("Cannot find comment with id " + commentId));
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public void addComment(String bookId, String commentText) {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new IllegalArgumentException("Cannot find book with id " + bookId));
        commentRepository.save(new Comment(commentText, book));
    }

    @Override
    @Transactional
    public void updateComment(String commentId, String text) {
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new IllegalArgumentException("Cannot find comment with id " + commentId));
        comment.setCommentString(text);
        commentRepository.save(comment);
    }
}
