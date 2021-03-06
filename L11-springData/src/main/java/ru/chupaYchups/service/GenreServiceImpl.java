package ru.chupaYchups.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chupaYchups.domain.Genre;
import ru.chupaYchups.repository.GenreRepository;
import ru.chupaYchups.dto.GenreDto;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private static class GenreDtoMapper implements Function<Genre, GenreDto> {
        @Override
        public GenreDto apply(Genre genre) {
            return new GenreDto(genre.getId(), genre.getName());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> getAllGenres() {
        return StreamSupport.stream(genreRepository.findAll().spliterator(), false).
            map(new GenreDtoMapper()).
            collect(Collectors.toList());
    }
}
