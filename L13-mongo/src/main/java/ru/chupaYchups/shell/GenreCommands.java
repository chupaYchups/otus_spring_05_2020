package ru.chupaYchups.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.chupaYchups.dto.GenreDto;
import ru.chupaYchups.service.GenreService;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;

    @ShellMethod(value = "Getting all genres.", key = "all-genres")
    public String getAllGenres() {
        return genreService.getAllGenres().
            stream().
            map(GenreDto::toString).
            collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Delete genre.", key = "del-genre")
    public String deleteGenre(@ShellOption("--id")String id) {
        genreService.delete(id);
        return "Genre with id " + id + "deleted successfully";
    }
}
