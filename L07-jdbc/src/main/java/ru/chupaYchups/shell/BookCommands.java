package ru.chupaYchups.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.chupaYchups.dto.BookDto;
import ru.chupaYchups.service.BookService;

import java.util.Optional;
import java.util.stream.Collectors;

@ShellComponent
public class BookCommands {

    private final BookService bookService;

    @Autowired
    public BookCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "Getting books", key={"gb", "gbook"})
    public String getBook(@ShellOption(value={"--a", "--author"}, defaultValue = ShellOption.NULL) String author,
        @ShellOption(value={"--g", "--genre"}, defaultValue = ShellOption.NULL) String genre,
        @ShellOption(value={"--n", "--name"}, defaultValue = ShellOption.NULL) String name) {

        return bookService.getAllBooks(Optional.ofNullable(author), Optional.ofNullable(genre), Optional.ofNullable(name)).
            stream().map(BookDto::toString).collect(Collectors.joining("\n"));
    }

    //public String addBook(String name, String author, String genre);

    //public String updateBookById(Long id, String name, String author, String genre);

    //public String deleteBookById(Long id, String name);
}