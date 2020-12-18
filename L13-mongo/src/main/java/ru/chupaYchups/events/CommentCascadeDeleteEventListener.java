package ru.chupaYchups.events;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.chupaYchups.domain.Comment;
import ru.chupaYchups.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class CommentCascadeDeleteEventListener extends AbstractMongoEventListener<Comment> {

    private final BookRepository bookRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Comment> event) {
        super.onAfterDelete(event);
        Document document = event.getSource();
        String commentId = document.get("_id").toString();
        bookRepository.clearCommentInfoFromBooks(commentId);
    }
}
