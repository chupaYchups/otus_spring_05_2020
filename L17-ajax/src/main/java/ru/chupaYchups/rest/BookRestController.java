package ru.chupaYchups.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.chupaYchups.dto.BookDto;
import ru.chupaYchups.service.BookService;
import java.util.List;
import java.util.Optional;

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
    }

    @GetMapping({"/book/{bookId}"})
    public BookDto getBook(@PathVariable String bookId) {
        return bookService.findById(Long.parseLong(bookId));
    }

    @PostMapping({"/book/{bookId}"})
    public String updateBook(@PathVariable String bookId, String name, String author, String genre) {
        bookService.updateBookById(Long.parseLong(bookId), Optional.of(name), Optional.of(author), Optional.of(genre));
        return "Book updated successfully!!!";
    }

    @PostMapping({"/book"})
    public String addBook(String name, String author, String genre) {
        bookService.addBook(name, author, genre);
        return "Book added successfully!!!";
    }
}
