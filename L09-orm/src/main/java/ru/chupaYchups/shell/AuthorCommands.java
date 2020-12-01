package ru.chupaYchups.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.chupaYchups.dto.AuthorDto;
import ru.chupaYchups.service.AuthorService;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final AuthorService authorService;

    @ShellMethod(value = "Getting all authors.", key = "all-authors")
    public String getAllAuthors() {
        return authorService.getAllAuthors().
            stream().
            map(AuthorDto::toString).
            collect(Collectors.joining("\n"));
    }
}
