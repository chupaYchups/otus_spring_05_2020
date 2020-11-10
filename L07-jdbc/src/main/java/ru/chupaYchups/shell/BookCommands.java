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

    @ShellMethod(value = "Getting books.", key={"gb", "gbook"})
    public String getBook(@ShellOption(value={"--a", "--author"}, defaultValue = ShellOption.NULL) String author,
            @ShellOption(value={"--g", "--genre"}, defaultValue = ShellOption.NULL) String genre,
            @ShellOption(value={"--n", "--name"}, defaultValue = ShellOption.NULL) String name) {
        return bookService.getAllBooks(Optional.ofNullable(author), Optional.ofNullable(genre), Optional.ofNullable(name)).
            stream().map(BookDto::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Adding book.", key ={"ab", "addBook"})
    public String addBook(@ShellOption(value={"--n", "--name"})String name,@ShellOption(value={"--a", "--author"})String author,@ShellOption(value={"--g", "--genre"})String genre) {
        bookService.addBook(name, author, genre);
        return "Book '" + name + "' sucessfully added";
    }

    @ShellMethod(value = "Updating book by identificator.", key ={"ub", "updatebook", "updbook"})
    public String updateBookById(@ShellOption Long id,
            @ShellOption(value = {"--n", "--name"})String name,
            @ShellOption(value = {"--a", "--author"})String author,
            @ShellOption(value = {"--g", "--genre"})String genre) {
        bookService.updateBookById(id, name, author, genre);
        return "Book with id '" + id + "' sucessfully updated";
    }

    @ShellMethod(value = "Deleting book by identificator.", key ={"db", "delbook", "deleteBook"})
    public String deleteBookById(Long id) {
        bookService.deleteBookById(id);
        return "Book with id '" + id + "' sucessfully deleted";
    }
}