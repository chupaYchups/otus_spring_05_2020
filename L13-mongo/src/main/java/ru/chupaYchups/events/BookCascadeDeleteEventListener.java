package ru.chupaYchups.events;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.repository.CommentRepository;

@RequiredArgsConstructor
@Component
public class BookCascadeDeleteEventListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        Document document = event.getSource();
        String bookId = document.get("_id").toString();
        commentRepository.removeBookComments(bookId);
    }
}
