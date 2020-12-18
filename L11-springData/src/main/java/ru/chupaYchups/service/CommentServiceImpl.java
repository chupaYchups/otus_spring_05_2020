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
    public List<CommentDto> getBookComments(long bookId) {
        Book book = bookRepository.findBookById(bookId);
        return book.getComments().
            stream().
            map(new CommentDtoMapper()).
            collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findCommentById(commentId);
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public void addComment(Long bookId, String commentText) {
        Book book = bookRepository.findBookById(bookId);
        commentRepository.save(new Comment(commentText, book));
    }

    @Override
    @Transactional
    public void updateComment(Long commentId, String text) {
        Comment comment = commentRepository.findCommentById(commentId);
        comment.setCommentString(text);
        commentRepository.save(comment);
    }
}
