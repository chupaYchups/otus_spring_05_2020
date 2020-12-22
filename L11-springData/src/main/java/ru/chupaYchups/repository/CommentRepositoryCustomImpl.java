package ru.chupaYchups.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.chupaYchups.domain.Comment;
import ru.chupaYchups.repository.exception.NoSuchCommentException;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final CommentRepository commentRepository;

    @Override
    public Comment findCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NoSuchCommentException(id));
    }
}
