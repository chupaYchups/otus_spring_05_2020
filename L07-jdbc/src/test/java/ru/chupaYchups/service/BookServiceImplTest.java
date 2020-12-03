package ru.chupaYchups.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.shell.standard.ShellComponent;
import ru.chupaYchups.dao.AuthorDao;
import ru.chupaYchups.dao.BookDao;
import ru.chupaYchups.dao.GenreDao;
import ru.chupaYchups.domain.Author;
import ru.chupaYchups.domain.Book;
import ru.chupaYchups.domain.Genre;
import ru.chupaYchups.dto.BookDto;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
@DisplayName("Тестирование того что сервис корректно")
class BookServiceImplTest {

    public static final String TEST_AUTHOR_NAME = "Test author";
    public static final String TEST_GENRE_NAME = "Test genre";
    public static final String TEST_AUTHOR_NAME_2 = "Test author 2";
    public static final String TEST_GENRE_NAME_2 = "Test genre 2";
    public static final long TEST_BOOK_ID = 1L;
    public static final long TEST_BOOK_ID_2 = 2l;
    public static final String TEST_BOOK_NAME = "Test book 1";
    public static final String TEST_BOOK_NAME_2 = "Test book 2";
    public static final String ID_FIELD = "id";
    public static final String NAME_FIELD = "name";
    public static final String AUTHOR_NAME_FIELD_PATH = "author.name";
    public static final String GENRE_NAME_FIELD_PATH = "genre.name";
    @MockBean
    private BookDao bookDao;
    @MockBean
    private AuthorDao authorDao;
    @MockBean
    private GenreDao genreDao;

    @Autowired
    private BookService bookService;

    private final static Author testAuthor = new Author(TEST_AUTHOR_NAME);
    private final static Author testAuthor2 = new Author(TEST_AUTHOR_NAME_2);
    private final static Genre testGenre = new Genre(TEST_GENRE_NAME);
    private final static Genre testGenre2 = new Genre(TEST_GENRE_NAME_2);

    @Configuration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ShellComponent.class))
    static class TestConfiguration {}

    @Test
    @DisplayName("возвращает все книги при пустых параметрах запроса")
    void testThatServiceCorrectlyQueryAllBooks() {
        List<Book> testBookList = List.of(new Book(TEST_BOOK_ID, TEST_BOOK_NAME, testAuthor, testGenre),
                new Book(TEST_BOOK_ID_2, TEST_BOOK_NAME_2, testAuthor2, testGenre2));

        Map<Long, Book> testBookMap = testBookList.stream().collect(Collectors.toMap(Book::getId, book -> book));
        given(bookDao.findBooks(Optional.empty(), Optional.empty(), Optional.empty())).willReturn(testBookList);

        List<BookDto> actualDtoList = bookService.findBooks(Optional.empty(), Optional.empty(), Optional.empty());

        assertThat(actualDtoList).hasSameSizeAs(testBookList).allSatisfy(bookDto -> checkDomainObjectEqualsDto(testBookMap.get(bookDto.getId()), bookDto));
        then(bookDao).should(Mockito.times(1)).findBooks(Mockito.any(Optional.class), Mockito.any(Optional.class), Mockito.any(Optional.class));
        Mockito.verifyNoInteractions(genreDao, authorDao);
        verifyNoMoreInteractions(bookDao);
    }

    @Test
    @DisplayName("не возвращает ничего когда нет в БД такого значения параметра поиска")
    void testThatServiceCorrectlyQueryNoBooksWhenHasNoSuchParam() {
        Book testBook = new Book(TEST_BOOK_ID, TEST_BOOK_NAME, testAuthor, testGenre);
        given(authorDao.findByName(testAuthor.getName())).willReturn(Optional.of(testAuthor));
        given(genreDao.findByName(testGenre.getName())).willReturn(Optional.empty());
        given(bookDao.findBooks(Optional.of(testBook.getAuthor()), Optional.of(testBook.getGenre()), Optional.of(testBook.getName()))).willReturn(List.of(testBook));

        List<BookDto> actualDtoList = bookService.findBooks(Optional.of(testBook.getAuthor().getName()), Optional.of(testBook.getGenre().getName()), Optional.of(testBook.getName()));

        assertThat(actualDtoList).isEmpty();
        InOrder inOrder = Mockito.inOrder(authorDao, genreDao, bookDao);
        then(authorDao).should(inOrder, times(1)).findByName(anyString());
        then(genreDao).should(inOrder, times(1)).findByName(anyString());
        then(bookDao).should(inOrder, times(1)).findBooks(any(Optional.class), any(Optional.class), any(Optional.class));
        verifyNoMoreInteractions(authorDao, genreDao, bookDao);
    }

    @Test
    @DisplayName("возвращает записи соответствующие параметрам запроса")
    void testThatServiceCorrectlyQueryBooksByParameters() {
        Book testBook = new Book(TEST_BOOK_ID, TEST_BOOK_NAME, testAuthor, testGenre);
        given(authorDao.findByName(testAuthor.getName())).willReturn(Optional.of(testAuthor));
        given(genreDao.findByName(testGenre.getName())).willReturn(Optional.of(testGenre));
        given(bookDao.findBooks(Optional.of(testBook.getAuthor()), Optional.of(testBook.getGenre()), Optional.of(testBook.getName()))).willReturn(List.of(testBook));

        List<BookDto> actualDtoList = bookService.findBooks(Optional.of(testBook.getAuthor().getName()), Optional.of(testBook.getGenre().getName()), Optional.of(testBook.getName()));

        assertThat(actualDtoList).hasSize(1);
        BookDto actualBookDTO = actualDtoList.get(0);
        checkDomainObjectEqualsDto(testBook, actualBookDTO);

        InOrder inOrder = Mockito.inOrder(authorDao, genreDao, bookDao);
        then(authorDao).should(inOrder, times(1)).findByName(anyString());
        then(genreDao).should(inOrder, times(1)).findByName(anyString());
        then(bookDao).should(inOrder, times(1)).findBooks(any(Optional.class), any(Optional.class), any(Optional.class));
        verifyNoMoreInteractions(authorDao, genreDao, bookDao);
    }

    private void checkDomainObjectEqualsDto(Book testBook, BookDto actualBookDTO) {
        assertThat(testBook).
            extracting(ID_FIELD, NAME_FIELD, AUTHOR_NAME_FIELD_PATH, GENRE_NAME_FIELD_PATH).
            containsExactly(actualBookDTO.getId(),
                actualBookDTO.getName(),
                actualBookDTO.getAuthor(),
                actualBookDTO.getGenre());
    }

    @Test
    @DisplayName("обновляет запись по идентификатору")
    void testThatServiceCorrectlyUpdateBookById() {
        Book testBookToUpdate = new Book(TEST_BOOK_ID, TEST_BOOK_NAME, testAuthor, testGenre);
        Book testBookExpected = new Book(TEST_BOOK_ID, TEST_BOOK_NAME_2, testAuthor, testGenre2);
        given(bookDao.findById(TEST_BOOK_ID)).willReturn(Optional.of(testBookToUpdate));
        given(genreDao.findByName(TEST_GENRE_NAME_2)).willReturn(Optional.empty());
        given(genreDao.insert(testGenre2)).willReturn(testGenre2);

        //Обновляем имя книги и жанр
        bookService.updateBookById(testBookExpected.getId(), Optional.of(testBookExpected.getName()), Optional.empty(), Optional.of(testBookExpected.getGenre().getName()));

        InOrder inOrder = inOrder(bookDao, authorDao, genreDao);
        then(bookDao).should(inOrder, times(1)).findById(TEST_BOOK_ID);
        then(genreDao).should(inOrder, times(1)).findByName(TEST_GENRE_NAME_2);
        then(genreDao).should(inOrder, times(1)).insert(testGenre2);
        then(bookDao).should(inOrder, times(1)).update(eq(testBookExpected));
        verifyNoMoreInteractions(authorDao, genreDao, bookDao);
    }

    @Test
    @DisplayName("удаляет запись по идентификатору")
    void testThatServiceCorrectlyDeleteBookById() {
        Book testBookToDelete = new Book(TEST_BOOK_ID, TEST_BOOK_NAME, testAuthor, testGenre);
        given(bookDao.findById(testBookToDelete.getId())).willReturn(Optional.of(testBookToDelete));

        bookService.deleteBookById(testBookToDelete.getId());

        then(bookDao).should(times(1)).findById(testBookToDelete.getId());
        then(bookDao).should(times(1)).delete(testBookToDelete);
        verifyNoMoreInteractions(bookDao);
    }

    @Test
    @DisplayName("выбрасывает исключение в случае передачи не существующего идентификатора для удаления")
    void testThatServiceThrowsExceptionWhereNotExistsIdOnDelete() {
        long notExistsId = 3L;
        given(bookDao.findById(notExistsId)).willReturn(Optional.empty());

        assertThatIllegalArgumentException().isThrownBy(() -> {
            bookService.deleteBookById(notExistsId);
        }).withMessage("Cannot find book with id: " + notExistsId);

        then(bookDao).should(never()).delete(any(Book.class));
    }
}