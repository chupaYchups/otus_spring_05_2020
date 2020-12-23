package ru.chupaYchups.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chupaYchups.dto.BookDto;
import ru.chupaYchups.service.BookService;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping({"/"})
    public String listPage(Model model) {
        List<BookDto> bookDtoList = bookService.getAllBooks();
        model.addAttribute("books", bookDtoList);
        return "bookList";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam(required = false)Long id, Model model) {
        model.addAttribute("book", id != null ? bookService.findById(id) : new BookDto());
        return "bookEdit";
    }

    @PostMapping("/edit")
    public String updateBook(@RequestParam long id, String name, String author, String genre) {
        if (id <= 0) {
            bookService.addBook(name, author, genre);
        } else {
            bookService.updateBookById(id, Optional.of(name), Optional.of(author), Optional.of(genre));
        }
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam long id) {
        bookService.deleteBookById(id);
        return "redirect:/";
    }
}
