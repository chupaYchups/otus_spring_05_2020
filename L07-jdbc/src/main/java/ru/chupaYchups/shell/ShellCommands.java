package ru.chupaYchups.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.chupaYchups.service.BookService;

@ShellComponent
public class ShellCommands {

    private final BookService bookService;

    @Autowired
    public ShellCommands(BookService bookService) {
        this.bookService = bookService;
    }


    @ShellMethod(value = "getBook", key="gb, gbook")
    public String getBook(@ShellOption String author, @ShellOption String genre) {
        return bookService.getAllBooks(author, genre).toString();
    }
}