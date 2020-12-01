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

    @ShellMethod(value = "Finding books.", key="find-book")
    public String getBook(@ShellOption(value={"--a", "--author"}, defaultValue = ShellOption.NULL) String author,
            @ShellOption(value={"--g", "--genre"}, defaultValue = ShellOption.NULL) String genre,
            @ShellOption(value={"--n", "--name"}, defaultValue = ShellOption.NULL) String name) {
        return bookService.findBooks(Optional.ofNullable(author), Optional.ofNullable(genre), Optional.ofNullable(name)).
            stream().map(BookDto::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Show all books.", key="all-books")
    public String allBooks() {
        return bookService.getAllBooks().stream().map(BookDto::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Adding book.", key = "add-book")
    public String addBook(@ShellOption(value={"--n", "--name"})String name,@ShellOption(value={"--a", "--author"})String author,@ShellOption(value={"--g", "--genre"})String genre) {
        bookService.addBook(name, author, genre);
        return "Book '" + name + "' sucessfully added";
    }

    @ShellMethod(value = "Updating book by identificator.", key ="update-book")
    public String updateBookById(@ShellOption Long id,
            @ShellOption(value = {"--n", "--name"}, defaultValue = ShellOption.NULL)String name,
            @ShellOption(value = {"--a", "--author"}, defaultValue = ShellOption.NULL)String author,
            @ShellOption(value = {"--g", "--genre"}, defaultValue = ShellOption.NULL)String genre) {
        bookService.updateBookById(id, Optional.ofNullable(name),
            Optional.ofNullable(author), Optional.ofNullable(genre));
        return "Book with id '" + id + "' sucessfully updated";
    }

    @ShellMethod(value = "Deleting book by identificator.", key = "delete-book")
    public String deleteBookById(Long id) {
        bookService.deleteBookById(id);
        return "Book with id '" + id + "' sucessfully deleted";
    }
}