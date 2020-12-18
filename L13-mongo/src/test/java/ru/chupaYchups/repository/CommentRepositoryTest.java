package ru.chupaYchups.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.chupaYchups.mongock.test.TestDatabaseChangeLog.*;

@DataMongoTest
@DisplayName("Тестирование того, что репозиторий комментариев корректно")
class CommentRepositoryTest {

    private static final String TEST_COMMENT_STRING = "test comment";
    private static final String NEW_TEST_COMMENT_STRING = "new test comment";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("сохраняет новую сущность")
    void testThatRepositoryCorrectlySaveNewComment() {
        Book testBook = mongoTemplate.findById(WAR_AND_PEACE_BOOK_ID, Book.class);

        Comment savedComment = commentRepository.save(new Comment(TEST_COMMENT_STRING, testBook));

        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getId()).isNotNull();
        Comment persistedComment = mongoTemplate.findById(savedComment.getId(), Comment.class);
        assertThat(persistedComment).isNotNull().isEqualToComparingFieldByField(savedComment);
    }

    @Test
    @DisplayName("обновляет сущность")
    void testThatRepositoryCorrectlyUpdateComment() {
        Comment testComment = mongoTemplate.findById(COMMENT_TEST_ID_WP_GOOD, Comment.class);
        testComment.setCommentString(NEW_TEST_COMMENT_STRING);

        commentRepository.save(testComment);

        Comment persistedComment = mongoTemplate.findById(testComment.getId(), Comment.class);
        assertThat(persistedComment).isNotNull().isEqualToComparingFieldByField(testComment);
    }

    @Test
    @DisplayName("ищет комментарий по идентификатору")
    void testThatRepositoryCorrectlyFindCommentById() {
        Comment foundComment = mongoTemplate.findById(COMMENT_TEST_ID_WP_GOOD, Comment.class);

        Optional<Comment> testCommentOptional = commentRepository.findById(COMMENT_TEST_ID_WP_GOOD);

        assertThat(testCommentOptional).isPresent().get().isEqualToComparingFieldByField(foundComment);
    }

    @Test
    @DisplayName("удаляет комментарий")
    void testThatRepositoryCorrectlyDeleteComment() {
        Comment commentToDelete = mongoTemplate.findById(COMMENT_TEST_ID_WP_GOOD, Comment.class);

        commentRepository.delete(commentToDelete);

        commentToDelete = mongoTemplate.findById(commentToDelete.getId(), Comment.class);
        assertThat(commentToDelete).isNull();
    }
    //todo удалили же ж метод
/*
    @Test
    @DisplayName("ищет комментарии к книге")
    void testThatRepositoryCorrectlyFindCommentsByBook() {
        Book bookWithComments = mongoTemplate.findById(COMMENT_TEST_ID_WP_GOOD, Book.class);
        Comment testCommentFirst = testEntityManager.find(Comment.class, TEST_COMMENT_FIRST_ID);
        Comment testCommentSecond = testEntityManager.find(Comment.class, TEST_COMMENT_SECOND_ID);

        List<Comment> bookComments = commentRepository.findByBook(bookWithComments);

        assertThat(bookComments).isNotEmpty().contains(testCommentFirst, testCommentSecond);
    }*/
}