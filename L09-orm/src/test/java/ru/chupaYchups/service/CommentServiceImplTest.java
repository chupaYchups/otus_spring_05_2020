package ru.chupaYchups.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Comment;
import ru.chupaYchups.domain.Genre;
import ru.chupaYchups.dto.CommentDto;
import ru.chupaYchups.repository.BookRepository;
import ru.chupaYchups.repository.CommentRepository;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest
@DisplayName("Тест того, что сервис комментариев корректно")
class CommentServiceImplTest {

    private final static long TEST_BOOK_ID = 1L;
    private final static long TEST_COMMENT_ID = 1L;
    private static final String COMMENT_STRING = "commentString";
    private static final String TEST_COMMENT_FIRST_STRING = "test comment first";
    private static final String TEST_COMMENT_SECOND_STRING = "test comment second";
    private static final String TEST_BOOK_NAME = "test book";
    private static final String TEST_AUTHOR_NAME = "test author";
    private static final String TEST_GENRE_NAME = "test genre";

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("начитывает список комментариев по идентификатору книги")
    void testThatServiceCorrectlyFindBookComments() {
        final Book testBook = new Book(TEST_BOOK_NAME, new Author(TEST_AUTHOR_NAME), new Genre(TEST_GENRE_NAME));
        List<Comment> testCommentList = List.of(new Comment(TEST_COMMENT_FIRST_STRING, testBook),
            new Comment(TEST_COMMENT_SECOND_STRING, testBook));
        testBook.setComments(testCommentList);
        given(bookRepository.findById(TEST_BOOK_ID)).willReturn(Optional.of(testBook));
        given(commentRepository.findByBook(testBook)).willReturn(testCommentList);

        List<CommentDto> commentDtoList = commentService.getBookComments(TEST_BOOK_ID);

        assertThat(commentDtoList).hasSize(2);

        assertThat(commentDtoList).
                extracting(COMMENT_STRING).
                containsExactly(TEST_COMMENT_FIRST_STRING, TEST_COMMENT_SECOND_STRING);
    }

    @Test
    @DisplayName("выбрасывает исключение когда ищутся комментарии по несуществующей книге")
    void testThatServiceThrowExceptionWhenGettingCommentsForNotExistsBook() {
        final Book testBook = new Book(TEST_BOOK_NAME, new Author(TEST_AUTHOR_NAME), new Genre(TEST_GENRE_NAME));
        List<Comment> testCommentList = List.of(new Comment(TEST_COMMENT_FIRST_STRING, testBook),
                new Comment(TEST_COMMENT_SECOND_STRING, testBook));
        testBook.setComments(testCommentList);
        given(bookRepository.findById(TEST_BOOK_ID)).willReturn(Optional.empty());

        assertThatIllegalArgumentException().isThrownBy(() -> {
            commentService.getBookComments(TEST_BOOK_ID);
        }).withMessage("Cannot find book with id " + TEST_BOOK_ID);
    }

    @Test
    @DisplayName("добавляет комментарий")
    void testThatServiceCorrectlyAddComment() {
        final Book testBook = new Book(TEST_BOOK_NAME, new Author(TEST_AUTHOR_NAME), new Genre(TEST_GENRE_NAME));
        final Comment commentToAdd = new Comment(TEST_COMMENT_FIRST_STRING, testBook);
        given(bookRepository.findById(TEST_BOOK_ID)).willReturn(Optional.of(testBook));

        commentService.addComment(TEST_BOOK_ID, TEST_COMMENT_FIRST_STRING);

        then(commentRepository).should(times(1)).save(eq(commentToAdd));
    }

    @Test
    @DisplayName("выбрасывает исключение при попытке добавить комментарий к несуществующей книге")
    void testThatServiceThrowsExceptionWhenAddCommentToNotExistsBook() {
        given(bookRepository.findById(TEST_BOOK_ID)).willReturn(Optional.empty());

        assertThatIllegalArgumentException().isThrownBy(() -> {
            commentService.addComment(TEST_BOOK_ID, TEST_COMMENT_FIRST_STRING);
        }).withMessage("Cannot find book with id " + TEST_BOOK_ID);
    }

    @Test
    @DisplayName("удаляет комментарий")
    void testThatServiceCorrectlyDeleteComment() {
        final Book testBook = new Book(TEST_BOOK_NAME, new Author(TEST_AUTHOR_NAME), new Genre(TEST_GENRE_NAME));
        final Comment commentToDelete = new Comment(TEST_COMMENT_FIRST_STRING, testBook);
        given(bookRepository.findById(TEST_BOOK_ID)).willReturn(Optional.of(testBook));
        given(commentRepository.findById(TEST_COMMENT_ID)).willReturn(Optional.of(commentToDelete));

        commentService.deleteComment(TEST_COMMENT_ID);

        then(commentRepository).should(times(1)).delete(eq(commentToDelete));
    }

    @Test
    @DisplayName("выбрасывает исключение при попытке удаление не существующего комментария")
    void testThatServiceThrowsExceptionWhenTryToDeleteNotExistsComment() {
        final Book testBook = new Book(TEST_BOOK_NAME, new Author(TEST_AUTHOR_NAME), new Genre(TEST_GENRE_NAME));
        given(bookRepository.findById(TEST_BOOK_ID)).willReturn(Optional.of(testBook));
        given(commentRepository.findById(TEST_COMMENT_ID)).willReturn(Optional.empty());

        assertThatIllegalArgumentException().isThrownBy(() -> {
            commentService.deleteComment(TEST_COMMENT_ID);
        }).withMessage("Cannot find comment with id " + TEST_COMMENT_ID);
    }

    @Test
    @DisplayName("обновляет комментарий")
    void testThatServiceCorrectlyUpdateComment() {
        final Book testBook = new Book(TEST_BOOK_NAME, new Author(TEST_AUTHOR_NAME), new Genre(TEST_GENRE_NAME));
        final Comment commentToUpdate = new Comment(TEST_COMMENT_FIRST_STRING, testBook);
        given(commentRepository.findById(TEST_COMMENT_ID)).willReturn(Optional.of(commentToUpdate));

        commentService.updateComment(TEST_COMMENT_ID, TEST_COMMENT_SECOND_STRING);

        ArgumentCaptor<Comment> commentCaptor = ArgumentCaptor.forClass(Comment.class);
        then(commentRepository).should().save(commentCaptor.capture());
        assertThat(commentCaptor.getValue().getCommentString()).isEqualTo(TEST_COMMENT_SECOND_STRING);
    }
}