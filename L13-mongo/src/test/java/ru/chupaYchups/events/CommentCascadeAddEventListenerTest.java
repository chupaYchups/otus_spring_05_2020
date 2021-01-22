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
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static ru.chupaYchups.mongock.test.TestDatabaseChangeLog.WAR_AND_PEACE_BOOK_ID;

@DataMongoTest
@ComponentScan("ru.chupaYchups.events")
@DisplayName("Тестирование того что листенер добавления комментария")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class CommentCascadeAddEventListenerTest {

    private static final String NEW_TEST_COMMENT = "new test comment";
    private static final String ID_FIELD = "id";
    private static final String COMMENT_STRING_FIELD = "commentString";
    private static final String BOOK_ID_PATH = "book.id";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @DisplayName("добавляет ссылку на него в книгу")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testThatCommentCascadeAddsToBook() {
        Comment testComment = new Comment(NEW_TEST_COMMENT, mongoTemplate.findById(WAR_AND_PEACE_BOOK_ID, Book.class));

        mongoTemplate.insert(testComment);

        assertThat(mongoTemplate.findById(WAR_AND_PEACE_BOOK_ID, Book.class).getComments()).
            isNotEmpty().
            extracting(ID_FIELD, COMMENT_STRING_FIELD, BOOK_ID_PATH).
            contains(tuple(testComment.getId(), testComment.getCommentString(), testComment.getBook().getId()));
    }
}



