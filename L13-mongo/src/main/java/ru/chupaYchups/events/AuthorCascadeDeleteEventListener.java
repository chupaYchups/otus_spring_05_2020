package ru.chupaYchups.events;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class AuthorCascadeDeleteEventListener extends AbstractMongoEventListener<Author> {

    private final BookRepository bookRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Author> event) {
        super.onAfterDelete(event);
        Document document = event.getSource();
        String authorId = document.get("_id").toString();
        bookRepository.removeAuthorBooks(authorId);

    }
}
