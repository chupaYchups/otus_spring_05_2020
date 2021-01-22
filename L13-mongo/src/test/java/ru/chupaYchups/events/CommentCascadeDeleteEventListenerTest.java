package ru.chupaYchups.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;
import ru.chupaYchups.repository.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.chupaYchups.mongock.test.TestDatabaseChangeLog.WAR_AND_PEACE_BOOK_ID;

@DataMongoTest
@ComponentScan("ru.chupaYchups.events")
@DisplayName("Тестирование того что листенер удаления комментария")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class CommentCascadeDeleteEventListenerTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("удаляет битую ссылку на него из книги")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testThatCommentRefCascadeDeletesFromBook() {
        final int commentsQty = 2;
        Book toDeleteCommentBook = mongoTemplate.findById(WAR_AND_PEACE_BOOK_ID, Book.class);
        assertThat(toDeleteCommentBook.getComments()).isNotEmpty().hasSize(commentsQty);

        Comment commentToDelete = toDeleteCommentBook.getComments().get(1);
        mongoTemplate.remove(commentToDelete);

        assertThat(mongoTemplate.findById(commentToDelete.getId(), Comment.class)).isNull();

        //ожидаем что действительный размер массива был уменьшен на единицу. Ушла битая ссылка.
        assertThat(bookRepository.getCommentsArrayLengthById(toDeleteCommentBook.getId())).isEqualTo(commentsQty - 1);
    }

}