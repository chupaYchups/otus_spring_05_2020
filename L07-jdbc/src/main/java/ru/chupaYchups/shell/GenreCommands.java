package ru.chupaYchups.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.chupaYchups.dto.GenreDto;
import ru.chupaYchups.service.GenreService;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;

    @ShellMethod(value = "Getting all genres.", key = {"allgenres", "ag"})
    public String getAllGenres() {
        return genreService.getAllGenres().
            stream().
            map(GenreDto::toString).
            collect(Collectors.joining("\n"));
    }
}
