package ru.chupaYchups.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.chupaYchups.dto.BookDto;
import ru.chupaYchups.service.BookService;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping({"/book"})
    public List<BookDto> bookList() {
        List<BookDto> bookDtoList = bookService.getAllBooks();
        return bookDtoList;
    }

    @DeleteMapping({"/book/{bookId}"})
    public void deleteBook(@PathVariable String bookId) {
        bookService.deleteBookById(Long.parseLong(bookId));
        throw new RuntimeException("Asdasd");
    }
}
