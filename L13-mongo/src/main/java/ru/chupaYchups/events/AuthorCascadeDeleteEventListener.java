package ru.chupaYchups.events;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import ru.chupaYchups.domain.Author;

public class AuthorCascadeDeleteEventListener extends AbstractMongoEventListener<Author> {
    @Override
    public void onAfterDelete(AfterDeleteEvent<Author> event) {
        super.onAfterDelete(event);
    }
}
