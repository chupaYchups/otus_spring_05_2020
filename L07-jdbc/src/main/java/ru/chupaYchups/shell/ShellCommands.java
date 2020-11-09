package ru.chupaYchups.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.chupaYchups.service.BookService;

import java.util.Optional;

@ShellComponent
public class ShellCommands {

    private final BookService bookService;

    @Autowired
    public ShellCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "getBook", key={"gb", "gbook"})
    public String getBook(@ShellOption(defaultValue = ShellOption.NULL) String author, @ShellOption(defaultValue = ShellOption.NULL) String genre) {
       return bookService.getAllBooks(Optional.ofNullable(author), Optional.ofNullable(genre)).toString();
    }

    @ShellMethod(value = "hello", key={"h", "hl"})
    public String hello() {
        return "Hello Ivan!";
    }
}