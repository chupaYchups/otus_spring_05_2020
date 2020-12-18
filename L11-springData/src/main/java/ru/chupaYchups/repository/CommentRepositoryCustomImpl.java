package ru.chupaYchups.repository;

import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Comment;
import ru.chupaYchups.repository.exception.NoSuchCommentException;

@Repository
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private CommentRepository commentRepository;

    @Override
    public Comment findCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NoSuchCommentException(id));
    }
}
