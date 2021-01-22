package ru.chupaYchups.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;
import ru.chupaYchups.repository.BookRepository;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CommentCascadeAddEventListener  extends AbstractMongoEventListener<Comment> {

    private final BookRepository bookRepository;

    @Override
    public void onAfterSave(AfterSaveEvent<Comment> event) {
        Comment comment = event.getSource();
        Book book = comment.getBook();
        if (book.getComments() == null) {
            book.setComments(new ArrayList<>());
        }
        book.getComments().add(comment);
        bookRepository.save(book);
    }
}
