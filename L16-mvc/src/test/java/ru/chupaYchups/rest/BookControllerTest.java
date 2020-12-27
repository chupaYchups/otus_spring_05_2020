package ru.chupaYchups.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.chupaYchups.dto.BookDto;
import ru.chupaYchups.service.BookService;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.only;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(BookController.class)
@DisplayName("Тестирование того что контроллер книг корректно обрабатывает")
class BookControllerTest {

    private static final long TEST_BOOK_ID = 1l;
    private static final String TEST_AUTHOR = "Test author";
    private static final String TEST_GENRE = "Test genre";
    private static final String TEST_NAME = "Test name";
    private static final String BOOK_EDIT_FORM = "book-edit-form";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("запрос списочной формы книг")
    void testThatControllerCorrectProcessGetListPageRequest() throws Exception {
        BookDto testBookDto = new BookDto();
        testBookDto.setAuthor(TEST_AUTHOR);
        testBookDto.setGenre(TEST_GENRE);
        testBookDto.setName(TEST_NAME);
        given(bookService.getAllBooks()).willReturn(List.of(testBookDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/")).
            andExpect(MockMvcResultMatchers.status().isOk()).
            andExpect(MockMvcResultMatchers.content().string(containsString(TEST_AUTHOR))).
            andExpect(MockMvcResultMatchers.content().string(containsString(TEST_GENRE))).
            andExpect(MockMvcResultMatchers.content().string(containsString(TEST_NAME)));
    }

    @Test
    @DisplayName("открытие страницы редактирования и создания книги")
    void testThatControllerCorrectProcessGetEditPageRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/edit")).
            andExpect(MockMvcResultMatchers.status().isOk()).
            andExpect(MockMvcResultMatchers.content().string(containsString(BOOK_EDIT_FORM)));
    }

    @Test
    @DisplayName("обновление книги")
    void testThatControllerCorrectProcessPostUpdate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/edit").
                        queryParam("id", String.valueOf(TEST_BOOK_ID)).
                        param("name", TEST_NAME).
                        param("genre", TEST_GENRE).
                        param("author", TEST_AUTHOR).contentType(MediaType.APPLICATION_FORM_URLENCODED)).
        andExpect(MockMvcResultMatchers.status().isFound()).
        andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        then(bookService).should(only()).updateBookById(TEST_BOOK_ID, Optional.of(TEST_NAME), Optional.of(TEST_AUTHOR), Optional.of(TEST_GENRE));
    }

    @Test
    @DisplayName("удаление книги")
    void testThatControllerCorrectProcessDeleteRequest() {
    }
}