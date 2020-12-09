import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;
import ru.chupaYchups.repository.CommentRepository;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тестирование того, что репозиторий комментариев корректно")
class CommentRepositoryJpaTest {

    private static final String TEST_COMMENT_STRING = "test comment";
    private static final long TEST_COMMENT_ID = 1L;
    private static final String NEW_TEST_COMMENT_STRING = "new test comment";
    private static final long TEST_COMMENT_FIRST_ID = 1L;
    private static final long TEST_COMMENT_SECOND_ID = 2L;
    private static final long TEST_BOOK_ID = 1L;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("сохраняет новую сущность")
    void testThatRepositoryCorrectlySaveNewComment() {
        Book testBook = testEntityManager.find(Book.class, TEST_BOOK_ID);
        Comment savedComment = commentRepository.save(new Comment(TEST_COMMENT_STRING, testBook));
        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getId()).isNotNull();
        Comment persistedComment = testEntityManager.find(Comment.class, savedComment.getId());
        assertThat(persistedComment).isNotNull().isEqualToComparingFieldByField(savedComment);
    }

    @Test
    @DisplayName("обновляет сущность")
    void testThatRepositoryCorrectlyUpdateComment() {
        Comment testComment = testEntityManager.find(Comment.class, TEST_COMMENT_ID);
        testComment.setCommentString(NEW_TEST_COMMENT_STRING);
        commentRepository.save(testComment);
        Comment persistedComment = testEntityManager.find(Comment.class, TEST_COMMENT_ID);
        assertThat(persistedComment).isNotNull().isEqualToComparingFieldByField(testComment);
    }

    @Test
    @DisplayName("ищет комментарий по идентификатору")
    void testThatRepositoryCorrectlyFindCommentById() {
        Optional<Comment> testCommentOptional = commentRepository.findById(TEST_COMMENT_ID);
        Comment foundComment = testEntityManager.find(Comment.class, TEST_COMMENT_ID);
        assertThat(testCommentOptional).isPresent().get().isEqualToComparingFieldByField(foundComment);
    }

    @Test
    @DisplayName("удаляет комментарий")
    void testThatRepositoryCorrectlyDeleteComment() {
        Comment persistedComment = testEntityManager.find(Comment.class, TEST_COMMENT_ID);
        commentRepository.delete(persistedComment);
        persistedComment = testEntityManager.find(Comment.class, TEST_COMMENT_ID);
        assertThat(persistedComment).isNull();
    }

    @Test
    @DisplayName("ищет комментарии к книге")
    void testThatRepositoryCorrectlyFindCommentsByBook() {
        Book testBook = testEntityManager.find(Book.class, TEST_BOOK_ID);
        Comment testCommentFirst = testEntityManager.find(Comment.class, TEST_COMMENT_FIRST_ID);
        Comment testCommentSecond = testEntityManager.find(Comment.class, TEST_COMMENT_SECOND_ID);
        List<Comment> bookComments = commentRepository.findByBook(testBook);
        assertThat(bookComments).isNotEmpty().contains(testCommentFirst, testCommentSecond);
    }
}