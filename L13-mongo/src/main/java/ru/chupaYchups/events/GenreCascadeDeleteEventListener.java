package ru.chupaYchups.events;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.chupaYchups.domain.Genre;
import ru.chupaYchups.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class GenreCascadeDeleteEventListener extends AbstractMongoEventListener<Genre> {

    private final BookRepository bookRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Genre> event) {
        super.onAfterDelete(event);
        Document document = event.getSource();
        String genreId = document.get("_id").toString();
        bookRepository.removeGenreBooks(genreId);
    }
}
